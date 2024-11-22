package com.DFS.MasterNode.Models;

import java.util.*;

public class File {
    public String path;
    public int size;
    public int totalChunks;
    public UUID fileId;
    public HashSet<UUID> chunkIdList;
    public HashMap<UUID, UUID> primaryChunkServerMap;

    public File(){
        initialise();
    }

    private void initialise() {
        fileId = UUID.randomUUID();
        chunkIdList = new HashSet<>();
        primaryChunkServerMap = new HashMap<>();
    }

}
