package com.DFS.ClientNode.PartnerServices;


import com.DFS.ClientNode.Helpers.ConfigHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Service
public class HeartBeatService {

    public ResponseEntity<?> sendHearBeat(){

        HttpRequest request = GetRequest(ConfigHelper.MasterServerUrl + "/heartbeat");
        HttpResponse<String> response = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response.body());
    }

    private HttpRequest GetRequest(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8))
                .build();
    }

}
