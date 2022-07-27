package com.javatpoint.aspect;

import com.javatpoint.service.SFTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
