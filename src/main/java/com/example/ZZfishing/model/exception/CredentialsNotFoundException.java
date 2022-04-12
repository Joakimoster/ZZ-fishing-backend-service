package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public class CredentialsNotFoundException extends RequestException {

    public CredentialsNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public CredentialsNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }
}
