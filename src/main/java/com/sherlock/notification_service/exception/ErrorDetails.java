package com.sherlock.notification_service.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class ErrorDetails {
    @JsonProperty("error_code")
    private ErrorCode errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
    private Instant time;

    public ErrorDetails(){

    }

    public ErrorDetails(ErrorCode errorCode, String errorMessage, Instant time){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.time = time;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
