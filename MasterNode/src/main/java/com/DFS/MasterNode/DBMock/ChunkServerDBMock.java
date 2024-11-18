package com.DFS.MasterNode.DBMock;

import com.DFS.MasterNode.Helpers.Logger;
import com.DFS.MasterNode.Models.ChunckServer;
import com.DFS.MasterNode.Models.ChunkServer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 *I am lazy
 * use in Memory to Mock DB
 * use it to get some known chunk servers at startUp
 */
@Component
public class ChunkServerDBMock {
    private static List<ChunkServer> chunckServerList;
    private final String fakeUrl = "http://fake.chunk.server.com";

    public ChunkServerDBMock(){
        chunckServerList = new ArrayList<>();
        populateChunkServerWithDummy();
    }

    private void populateChunkServerWithDummy(){
        for(int i=0; i<3; i++) {
            chunckServerList.add(new ChunkServer()
                    .setId(UUID.randomUUID())
                    .setHeartBeatUrl(fakeUrl)
                    .setAliveStatus());
        }
        Logger.Info("Added Dummy ChunkServers");
    }

    public void AddChunkServer(ChunkServer chunkServer){
        if(chunckServerList.stream().anyMatch(cur -> cur.Id == chunkServer.Id)){
            return;
        }
        chunckServerList.add(chunkServer);
    }

    public List<ChunkServer> GetAllKnownChunk(){
        return chunckServerList;
    }

}
