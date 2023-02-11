package com.sherlock.notification_service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotificationCreateDto {

    @NotNull
    private Long type_id;

    @NotEmpty
    private String emailToSendTo;

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getEmailToSendTo() {
        return emailToSendTo;
    }

    public void setEmailToSendTo(String emailToSendTo) {
        this.emailToSendTo = emailToSendTo;
    }
}
