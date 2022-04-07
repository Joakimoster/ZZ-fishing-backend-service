package com.example.ZZfishing.api.history.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class HistoryNotDeletedException extends NotFoundException {

    public HistoryNotDeletedException(long id) {
        super(String.format("Unable to delete profile by id: %s", id));
    }
}
