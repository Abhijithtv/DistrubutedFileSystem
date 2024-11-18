package com.DFS.MasterNode.Tasks;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class PeriodicHeartBeatCheckerTask {

    @Value("${task.heart-beat.interval}")
    private long heartBeatCheckInterval;

    private final TaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    // This method will be called after the bean is initialized (i.e., after @PostConstruct)
    @PostConstruct
    public void init() {
        // Initialize the TaskScheduler
        ThreadPoolTaskScheduler scheduler = (ThreadPoolTaskScheduler) taskScheduler;
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("heart-beat-task-");
        scheduler.initialize();
        scheduler.scheduleAtFixedRate(this::sendHeartBeat, heartBeatCheckInterval);
    }

    // This is the method to send a heartbeat (the task logic)
    public void sendHeartBeat() {
        System.out.println("Sending heartbeat at: " + System.currentTimeMillis());
    }
}
