package com.sherlock.notification_service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class NotificationTypeUpdateDto {

    @NotBlank
    private String typeName;

    @NotBlank
    private String notificationContent;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }
}
