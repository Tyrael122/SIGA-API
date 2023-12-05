package com.makesoftware.siga.util;

import lombok.Getter;

@Getter
public enum Messages {
    SUBJECT_NOT_FOUND("Subject not found"),
    USER_NOT_FOUND("User not found"),
    INVALID_CREDENTIALS("Invalid credentials"),
    CPF_ALREADY_EXISTS("CPF already exists"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    USER_DELETED("User deleted"),
    SUBJECT_DELETED("Subject deleted");

    public static final String PASSWORD_MIN_SIZE = "Password must be at least 8 characters long";

    private final String message;

    Messages(String message) {
        this.message = message;
    }
}
