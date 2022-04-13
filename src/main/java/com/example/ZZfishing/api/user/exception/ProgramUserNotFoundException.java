package com.example.ZZfishing.api.user.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class ProgramUserNotFoundException extends NotFoundException {

    public ProgramUserNotFoundException(Long id) {
        super(String.format("Unable to find profile by id: %s", id));
    }
}
