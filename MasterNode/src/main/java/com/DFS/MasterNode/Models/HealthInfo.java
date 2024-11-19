package com.DFS.MasterNode.Models;

import com.DFS.MasterNode.Enums.HealthStatusEnum;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HealthInfo {
    public HealthStatusEnum status;
    public HashMap<UUID, ChunkHealth> chunkHealthHashMap;
}
