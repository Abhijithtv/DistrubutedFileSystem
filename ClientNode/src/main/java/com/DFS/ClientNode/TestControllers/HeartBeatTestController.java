package com.DFS.ClientNode.TestControllers;

import com.DFS.ClientNode.PartnerServices.HeartBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/test/heartbeat")
public class HeartBeatTestController {

    private final HeartBeatService heartBeatService;

    @Autowired
    public  HeartBeatTestController(HeartBeatService _heartbeatService){
        this.heartBeatService = _heartbeatService;
    }

    @GetMapping
    public ResponseEntity<?> SendHeartBeat(){
        return  heartBeatService.sendHearBeat();
    }

}
