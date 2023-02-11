package com.sherlock.notification_service.dto;

import javax.validation.constraints.NotNull;

public class ActivationMailDto {

    private String name;
    private String surname;
    private String email;
    private String link;

    public ActivationMailDto(String name, String surname, String email, String link){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
