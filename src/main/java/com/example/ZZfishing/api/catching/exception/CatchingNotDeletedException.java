package com.example.ZZfishing.api.catching.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class CatchingNotDeletedException extends NotFoundException {

    public CatchingNotDeletedException(long id) {
        super(String.format("Unable to find catching by id: %s", id));
    }
}
