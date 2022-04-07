package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public class NotDeletedException extends RequestException {

    public NotDeletedException(String message, HttpStatus status) {
        super(message, status);
    }

    public NotDeletedException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }
}
