package com.javatpoint.controller;

import com.javatpoint.service.Day0Runnable;
import com.javatpoint.service.SFTPService;
import com.javatpoint.service.TaskSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

@EnableScheduling
@RestController
@EnableAsync
@RequestMapping("/chatbot")
public class JobSchedulingController {

    @Autowired
    SFTPService sftpService;

    @Scheduled(cron = "*/20 * * * * *")
    public String dailyJob(){
        sftpService.send();
        return ResponseEntity.ok("abc").getBody();
    }

    @Autowired
    TaskScheduler taskScheduler;
    ScheduledFuture<?> scheduledFuture;

    @GetMapping("/sheduledjobmsgtable")
    public String jobS(){
        scheduledFuture = taskScheduler.scheduleAtFixedRate(() ->{
            // call method
            if(true){
                scheduledFuture.cancel(true);
            }
        },1000);

        return "success";
    }

    @Autowired
    TaskSchedulingService taskSchedulingService;

    @Autowired
    Day0Runnable day0Runnable;

    @GetMapping("/sheduledjobmsgtable1")
    public String jobSP(){
        taskSchedulingService.scheduleDayZero("jobId", day0Runnable, "/30 * * * * *");
        return "success";
    }

}
