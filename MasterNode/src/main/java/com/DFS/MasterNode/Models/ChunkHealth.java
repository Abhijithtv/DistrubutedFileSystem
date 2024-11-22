package com.DFS.MasterNode.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChunkHealth {
    public UUID chunkId;
    public int replications;
    public UUID primaryChunkServer;
    public List<UUID> chunkServerList;

    public ChunkHealth(UUID chunkId){
        this.chunkId = chunkId;
        this.replications = 0;
        this.primaryChunkServer = null;
        chunkServerList = new ArrayList<>();
    }

    public ChunkHealth incrementReplication(int count){
        this.replications+=count;
        return this;
    }

    public ChunkHealth setPrimaryChunkServer(UUID chunkServerId){
        this.primaryChunkServer = chunkServerId;
        return this;
    }

    public ChunkHealth addChunkServers(UUID chunkServerId) {
        this.chunkServerList.add(chunkServerId);
        return this;
    }

    public boolean hasSufficientCopy(int minCopyCount){
        return this.replications >= minCopyCount;
    }

    public int replicationShortage(int minCopyCount){
        return Math.min(0, minCopyCount - replications);
    }
}
