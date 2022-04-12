package com.example.ZZfishing.api.fishinglure.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class FishingLureNotFoundException extends NotFoundException {

    public FishingLureNotFoundException(long id) {
        super(String.format("Unable to find fishing lure by id: %s", id));
    }
}
