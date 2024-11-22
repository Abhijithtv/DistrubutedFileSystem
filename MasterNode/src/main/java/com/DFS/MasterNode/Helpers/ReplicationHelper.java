package com.DFS.MasterNode.Helpers;

import com.DFS.MasterNode.Models.Chunk;
import com.DFS.MasterNode.Models.ChunkServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReplicationHelper {
    public  static List<ChunkServer> findBackUpChunkServer(UUID chunkId, int count, List<ChunkServer> chunkServers){
        List<ChunkServer> res = new ArrayList<>();
        //TODO: Add server vacant space also in consideration
        //Hint: Use MAX-Heap

        for(ChunkServer chunkServer: chunkServers){
            if(chunkServer.isChunkPresent(chunkId)) continue;
            res.add(chunkServer);

            if(res.size()>=count) break;
        }

        return res;
    }

    public static void SendReplicationRequest(String url, boolean isPrimary, Chunk chunk){
        //get chunkDate using chunkId from primary Server.
        //sent replication req.
    }
}
