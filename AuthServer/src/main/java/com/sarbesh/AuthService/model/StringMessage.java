package com.sarbesh.AuthService.model;

import org.springframework.stereotype.Component;

@Component
public class StringMessage {

    private String message;

    @Override
    public String toString() {
        return "StringMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
