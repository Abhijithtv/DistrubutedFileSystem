package com.DFS.MasterNode.Services.GlobalStateService;

import com.DFS.MasterNode.DBMock.ChunkServerDBMock;
import com.DFS.MasterNode.Models.ChunkServer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class ChunkServerStateService {

    private final ChunkServerDBMock chunkServerDBMock;
    private static List<ChunkServer> chunkServerList = new ArrayList<>();

    @Autowired
    public ChunkServerStateService(ChunkServerDBMock chunkServerDBMock){
        this.chunkServerDBMock = chunkServerDBMock;
    }

    @PostConstruct
    private void init(){
        restoreSavedInfo();
    }

    private void restoreSavedInfo(){
        chunkServerList.addAll(this.chunkServerDBMock.GetAllKnownChunk());
    }

    public List<ChunkServer> getAllLiveChunkServers(){
        return chunkServerList;
    }

}
