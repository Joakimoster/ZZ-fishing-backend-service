package com.example.ZZfishing.model.exception;

import org.springframework.http.HttpStatus;

public class IdNotValidException extends RequestException {

    public IdNotValidException(long id) {
        super(String.format("ID: %s is not valid", id), HttpStatus.NOT_ACCEPTABLE);
    }
}
