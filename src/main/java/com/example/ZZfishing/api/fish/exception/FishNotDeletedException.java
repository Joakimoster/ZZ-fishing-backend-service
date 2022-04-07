package com.example.ZZfishing.api.fish.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class FishNotDeletedException extends NotFoundException {

    public FishNotDeletedException(long id) {
        super(String.format("Unable to delete fish by id: %s", id));
    }
}
