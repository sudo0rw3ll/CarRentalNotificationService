package com.sherlock.notification_service.scheduler;

import com.sherlock.notification_service.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private EmailService emailService;

    public Scheduler(EmailService emailService){
        this.emailService = emailService;
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void sendReminder(){
        System.out.println("Mail");
    }
}
