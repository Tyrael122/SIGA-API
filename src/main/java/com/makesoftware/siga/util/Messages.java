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
    SUBJECT_DELETED("Subject deleted"),
    COURSE_NOT_FOUND("Course not found"),
    COURSE_DELETED("Course deleted"),
    TEACHER_NOT_FOUND("Teacher not found"),
    TEACHER_DELETED("Teacher deleted"),
    TEACHABLE_SUBJECT_NOT_FOUND("Teachable subject not found"),
    TEACHABLE_SUBJECT_DELETED("Teachable subject deleted"),
    STUDENT_NOT_FOUND("Student not found"),
    STUDENT_DELETED("Student deleted"),
    TEACHABLE_COURSE_NOT_FOUND("Teachable course not found"),
    TEACHABLE_COURSE_DELETED("Teachable course deleted");

    public static final String PASSWORD_MIN_SIZE = "Password must be at least 8 characters long";

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
