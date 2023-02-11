package com.sherlock.notification_service.dto;

import javax.validation.constraints.NotNull;

public class NotificationDto {

    private Long id;

    private NotificationTypeDto notificationTypeDto;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationTypeDto getNotificationTypeDto() {
        return notificationTypeDto;
    }

    public void setNotificationTypeDto(NotificationTypeDto notificationTypeDto) {
        this.notificationTypeDto = notificationTypeDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
