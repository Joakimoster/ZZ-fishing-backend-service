package com.example.ZZfishing.api.history.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class HistoryNotFoundException extends NotFoundException {
    public HistoryNotFoundException(long id) {
        super(String.format("Unable to delete profile by id: %s", id));
    }
}
