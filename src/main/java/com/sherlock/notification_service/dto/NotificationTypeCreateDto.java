package com.sherlock.notification_service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotificationTypeCreateDto {

    @NotBlank
    private String type_name;

    @NotBlank
    private String notification_content;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getNotification_content() {
        return notification_content;
    }

    public void setNotification_content(String notification_content) {
        this.notification_content = notification_content;
    }
}
