package com.makesoftware.siga.util;

import lombok.Getter;

@Getter
public enum Messages {
    USER_NOT_FOUND("User not found"),
    INVALID_CREDENTIALS("Invalid credentials"),
    CPF_ALREADY_EXISTS("CPF already exists"),
    EMAIL_ALREADY_EXISTS("Email already exists");

    private final String message;

    Messages(String message) {
        this.message = message;
    }
}
