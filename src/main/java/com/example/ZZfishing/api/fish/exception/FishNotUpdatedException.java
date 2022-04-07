package com.example.ZZfishing.api.fish.exception;

import com.example.ZZfishing.model.exception.NotUpdatedException;

public class FishNotUpdatedException extends NotUpdatedException {

    public FishNotUpdatedException(long id) {
        super(String.format("Unable to update fish id: %s. %s", id));
    }
}
