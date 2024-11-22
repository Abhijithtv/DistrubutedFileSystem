package com.DFS.MasterNode.Models.HeartBeatModels;

import java.util.UUID;

public class PoorHealthChunk {
    public UUID chunkId;
    public int replicationShortage;

    public PoorHealthChunk(UUID chunkId, int replication){
        this.chunkId = chunkId;
        this.replicationShortage = replication;
    }

}
