package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public abstract class RequestException extends RuntimeException{

    private final HttpStatus status;

    public RequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
