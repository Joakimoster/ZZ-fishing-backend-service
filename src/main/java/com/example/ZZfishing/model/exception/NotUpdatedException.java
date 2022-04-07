package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public class NotUpdatedException extends RequestException {

    public NotUpdatedException(String message, HttpStatus status) {
        super(message, status);
    }

    public NotUpdatedException(String message) {
        this (message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
