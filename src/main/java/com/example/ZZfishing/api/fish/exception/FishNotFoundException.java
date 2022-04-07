package com.example.ZZfishing.api.fish.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class FishNotFoundException extends NotFoundException {

    public FishNotFoundException(long id) {
        super(String.format("Unable to find fish by id: %s", id));
    }
}
