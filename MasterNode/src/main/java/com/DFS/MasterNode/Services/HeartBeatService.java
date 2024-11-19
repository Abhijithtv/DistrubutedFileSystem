package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.Enums.HealthStatusEnum;
import com.DFS.MasterNode.Helpers.HeartBeatHelper;
import com.DFS.MasterNode.Models.ChunkServer;
import com.DFS.MasterNode.Models.Client;
import com.DFS.MasterNode.Models.HealthInfo;
import com.DFS.MasterNode.Models.HeartBeatResponse;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
            HeartBeatResponse response = send(server.HeartBeatUrl);
            heartBeatMap.put(server.Id, response);
        }

         HealthInfo systemHealthInfo = HeartBeatHelper.analyse(heartBeatMap, replicationFactor);

        //TODO: using the heartBeatMap,
        //analyse chuck health
        //even if bad, send heartBeat and update the state
        //but prioritize replication
        //prevent new replication, if its already in progress
        //get the system healthback
    }






}
