package com.sherlock.notification_service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotificationUpdateDto {

    @NotNull
    private Long type_id;

    @NotEmpty
    private String email;

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
