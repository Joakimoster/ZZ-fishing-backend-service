package com.example.ZZfishing.api.programuser.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class ProgramUserNotDeletedException extends NotFoundException {

    public ProgramUserNotDeletedException(long id) {
        super(String.format("Unable to find profile by id: %s", id));
    }
}
