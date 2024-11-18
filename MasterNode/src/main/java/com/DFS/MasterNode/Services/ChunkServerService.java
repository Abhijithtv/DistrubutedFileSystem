package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.DBMock.ChunkServerDBMock;
import com.DFS.MasterNode.Models.ChunkServer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkServerService {

    private static List<ChunkServer> currentServers;
    private final ChunkServerDBMock chunkServerDBMock;

    @Autowired
    public ChunkServerService(ChunkServerDBMock chunkServerDBMock){
        this.chunkServerDBMock = chunkServerDBMock;
    }

    @PostConstruct
    private void init(){
        currentServers = new ArrayList<>();
        currentServers.addAll(chunkServerDBMock.GetAllKnownChunk());
    }

    public void join(ChunkServer member){
        chunkServerDBMock.AddChunkServer(member);
        currentServers.add(member);
    }

    public List<ChunkServer> GetAll(){
        return currentServers;
    }

}
