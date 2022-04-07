package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RequestException {

    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public NotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }
}
