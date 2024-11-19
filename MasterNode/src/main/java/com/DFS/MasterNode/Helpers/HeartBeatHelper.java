package com.DFS.MasterNode.Helpers;

import com.DFS.MasterNode.Enums.HealthStatusEnum;
import com.DFS.MasterNode.Models.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HeartBeatHelper {

    private static HttpRequest getRequest(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public static HeartBeatResponse send(String url){
        HttpRequest request = getRequest(url);
        HeartBeatResponse  heartBeatResponse = new HeartBeatResponse();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            heartBeatResponse.chunkResponseList = new ObjectMapper()
                    .readValue(httpResponse.body(), new TypeReference<List<ChunkResponse>>() {});
            heartBeatResponse.isAlive = true;
        } catch (InterruptedException | IOException e) {
            heartBeatResponse.isAlive = false;
        }
        return heartBeatResponse;
    }

    //No need to find to analyse replication factor of all chunks that we told the chuck servers to copy
    //since, if none of the chunk sever has a copy, its lost
    //nothing can be done about it.
    public static HealthInfo analyse(HashMap<UUID, HeartBeatResponse> heartBeatMap, int replicationFactor){
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.chunkHealthHashMap = new HashMap<>();
        HashSet<UUID> chunksWithLowReplication = new HashSet<>();

        for(Map.Entry<UUID, HeartBeatResponse> entry: heartBeatMap.entrySet()){
            UUID chunkServerId = entry.getKey();
            HeartBeatResponse response = entry.getValue();

            if(!response.isAlive) {
                continue;
            }

            for(ChunkResponse chunkResponse : response.chunkResponseList){
                ChunkHealth chunkHealth = healthInfo.chunkHealthHashMap
                        .getOrDefault(chunkResponse.chunkId, new ChunkHealth(chunkResponse.chunkId))
                        .addChunkServers(chunkServerId)
                        .incrementReplication(1);

                if(chunkResponse.isPrimaryChunk){
                    chunkHealth.setPrimaryChunkServer(chunkServerId);
                }

                updateChunksWithLowReplication(chunkHealth, replicationFactor,  chunksWithLowReplication);
                healthInfo.chunkHealthHashMap.put(chunkResponse.chunkId, chunkHealth);
            }
        }

        healthInfo.status = !chunksWithLowReplication.isEmpty() ?
                HealthStatusEnum.Critical :
                HealthStatusEnum.Best;

        return healthInfo;
    }

    private static void updateChunksWithLowReplication(ChunkHealth chunkHealth, int replicationFactor, HashSet<UUID> chunksWithLowReplication){
        if(chunkHealth.replications < replicationFactor){
            chunksWithLowReplication.add(chunkHealth.chunkId);
        } else{
            chunksWithLowReplication.remove(chunkHealth.chunkId);
        }
    }

}




