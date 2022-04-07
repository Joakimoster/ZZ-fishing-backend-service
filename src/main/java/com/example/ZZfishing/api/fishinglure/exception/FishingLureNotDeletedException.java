package com.example.ZZfishing.api.fishinglure.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class FishingLureNotDeletedException extends NotFoundException {

    public FishingLureNotDeletedException(long id) {
        super(String.format("Unable to find profile by id: %s", id));
    }
}
