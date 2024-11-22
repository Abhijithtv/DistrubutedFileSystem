package com.DFS.MasterNode.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/*
* They are the people with the actual data
* Master just keep track of what they have
*/
public class ChunkServer {
    public UUID Id;
    private String heartBeatUrl;
    private Boolean isAlive;
    private String backupUrl;
    private HashSet<UUID> chunksPresent; //TODO: set value

    public ChunkServer(){
        this.chunksPresent = new HashSet<>();
    }

    public boolean isChunkPresent(UUID id){
        return chunksPresent.contains(id);
    }


    public ChunkServer setId(UUID id){
        this.Id = id;
        return this;
    }

    public ChunkServer setHeartBeatUrl(String url){
        this.heartBeatUrl = url;
        return this;
    }

    public String getHeartBeatUrl(){
        return heartBeatUrl;
    }

    public ChunkServer setBackUpUrl(String url){
        this.backupUrl = url;
        return this;
    }


    public ChunkServer setAliveStatus(){
        this.isAlive = true;
        return this;
    }

    public String getBackupUrl(){
        return backupUrl;
    }

}
