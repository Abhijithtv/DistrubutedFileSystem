package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.Enums.HealthStatusEnum;
import com.DFS.MasterNode.Helpers.HeartBeatHelper;
import com.DFS.MasterNode.Models.*;
import com.DFS.MasterNode.Models.HeartBeatModels.PoorHealthChunk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.DFS.MasterNode.Helpers.HeartBeatHelper.send;

@Service
public class HeartBeatService {

    private final ChunkServerService chunkServerService;

    public HeartBeatService(ChunkServerService chunkServerService){
        this.chunkServerService =  chunkServerService;
    }

    @Value("chunk.replication-factor")
    private int replicationFactor;

    public void SendHeartBeat(){
        HashMap<UUID, HeartBeatResponse> heartBeatMap = new HashMap<>();
        for(ChunkServer server : chunkServerService.GetAll()){
            HeartBeatResponse response = send(server.getHeartBeatUrl());
            heartBeatMap.put(server.Id, response);
        }

         HealthInfo systemHealthInfo = HeartBeatHelper.analyse(heartBeatMap, replicationFactor);

        //update State based on HashMap

        if(systemHealthInfo.status != HealthStatusEnum.Best){
            List<PoorHealthChunk> poorHealthChunk = new ArrayList<>();
            for (Map.Entry<UUID, ChunkHealth> entry: systemHealthInfo.chunkHealthHashMap.entrySet()){
                UUID chunkId = entry.getKey();
                ChunkHealth chunkHealth = entry.getValue();
                if(chunkHealth.hasSufficientCopy(replicationFactor)) continue;
                int replicaShortage = chunkHealth.replicationShortage(replicationFactor);
                poorHealthChunk.add( new PoorHealthChunk(chunkId, replicaShortage));
            }

        }



        //TODO: using the heartBeatMap,
        //analyse chuck health
        //even if bad, send heartBeat and update the state
        //but prioritize replication
        //prevent new replication, if its already in progress
        //get the system healthback
    }






}
