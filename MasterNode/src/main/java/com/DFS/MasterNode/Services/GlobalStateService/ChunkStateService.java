package com.DFS.MasterNode.Services.GlobalStateService;

import com.DFS.MasterNode.Models.Chunk;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class ChunkStateService {
    private HashMap<UUID, Chunk> chunkMap;
    private HashMap<UUID, List<UUID>> chunkToServerMap;

    public ChunkStateService(){
        chunkMap = new HashMap<>();
        chunkToServerMap = new HashMap<>();
    }

    public Chunk getChunkById(UUID id){
        return chunkMap.getOrDefault(id, null);
    }


}
