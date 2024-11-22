package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.Helpers.ReplicationHelper;
import com.DFS.MasterNode.Models.Chunk;
import com.DFS.MasterNode.Models.ChunkServer;
import com.DFS.MasterNode.Models.HeartBeatModels.PoorHealthChunk;
import com.DFS.MasterNode.Services.GlobalStateService.ChunkServerStateService;
import com.DFS.MasterNode.Services.GlobalStateService.ChunkStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReplicationService {

    private boolean replicationInProgress;
    private final ChunkServerStateService chunkServerStateService;
    private final ChunkStateService chunkStateService;

    @Autowired
    public ReplicationService(ChunkServerStateService chunkServerStateService, ChunkStateService chunkStateService){
        this.chunkServerStateService = chunkServerStateService;
        this.chunkStateService = chunkStateService;
        this.replicationInProgress = false;
    }

    public synchronized boolean isReplicationInProgress(){
        return replicationInProgress;
    }

    public synchronized void markReplicationInProgress(){
        replicationInProgress = true;
    }

    public synchronized  void markReplicationComplete(){
        replicationInProgress = false;
    }

    public void triggerReplication(List<PoorHealthChunk> chunkIdList){
        List<ChunkServer> chunkServerList = this.chunkServerStateService.getAllLiveChunkServers();
        for(PoorHealthChunk chunk: chunkIdList){
            List<ChunkServer> backUpservers = ReplicationHelper.findBackUpChunkServer(chunk.chunkId
                    , chunk.replicationShortage
                    , chunkServerList);
            performReplication(chunkStateService.getChunkById(chunk.chunkId),
                    backUpservers);
        }
    }

    private void performReplication(Chunk chunkToCopy, List<ChunkServer> backupChunkServerList){

        if(isReplicationInProgress()){
            return;
        }

        markReplicationInProgress();

        for(ChunkServer backUpServer: backupChunkServerList){
            ReplicationHelper.SendReplicationRequest(backUpServer.getBackupUrl()
                    ,false
                    , chunkToCopy);
        }

        markReplicationComplete();
    }


}
