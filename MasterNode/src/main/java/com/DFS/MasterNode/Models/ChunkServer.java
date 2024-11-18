package com.DFS.MasterNode.Models;

import java.util.UUID;

/*
* They are the people with the actual data
* Master just keep track of what they have
*/
public class ChunkServer {
    public UUID Id;
    public String HeartBeatUrl;
    public Boolean IsAlive;

    public ChunkServer setId(UUID id){
        this.Id = id;
        return this;
    }

    public ChunkServer setHeartBeatUrl(String url){
        this.HeartBeatUrl = url;
        return this;
    }

    public ChunkServer setAliveStatus(){
        this.IsAlive = true;
        return this;
    }
}
