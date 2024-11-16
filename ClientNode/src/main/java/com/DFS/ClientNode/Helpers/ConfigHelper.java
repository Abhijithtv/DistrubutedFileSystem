package com.DFS.ClientNode.Helpers;

import org.springframework.beans.factory.annotation.Value;

public class ConfigHelper {

    @Value("${heartbeat.service.url}")
    public static  String MasterServerUrl;
}
