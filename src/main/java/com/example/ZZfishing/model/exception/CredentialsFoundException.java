package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public class CredentialsFoundException extends RequestException {

    public CredentialsFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public CredentialsFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }
}
