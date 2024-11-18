package com.DFS.MasterNode.Helpers;

import com.DFS.MasterNode.Models.Chunk;
import com.DFS.MasterNode.Models.HeartBeatResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HeartBeatHelper {

    private static HttpRequest GetRequest(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public static HeartBeatResponse send(String url){
        HttpRequest request = GetRequest(url);
        HeartBeatResponse  heartBeatResponse = new HeartBeatResponse();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            heartBeatResponse.chunkList = new ObjectMapper()
                    .readValue(httpResponse.body(), new TypeReference<List<Chunk>>() {});
            heartBeatResponse.isAlive = true;
        } catch (InterruptedException | IOException e) {
            heartBeatResponse.isAlive = false;
        }
        return heartBeatResponse;
    }
}
