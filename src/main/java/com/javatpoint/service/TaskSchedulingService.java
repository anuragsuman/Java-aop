package com.javatpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    final long delayTime = 1000;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleDayZero(String jobId, Runnable tasklet, String cronExpression){
        ScheduledFuture<?> scheduledTask = taskScheduler.scheduleAtFixedRate(tasklet, delayTime);
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }

}
