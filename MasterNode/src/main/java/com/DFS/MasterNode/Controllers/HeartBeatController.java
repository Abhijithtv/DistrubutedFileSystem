package com.DFS.MasterNode.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
public class HeartBeatController {

    @PostMapping()
    public boolean HearBeatLogger(){
        return true;
    }
}
