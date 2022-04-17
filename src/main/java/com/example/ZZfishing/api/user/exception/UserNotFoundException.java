package com.example.ZZfishing.api.user.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Long id) {
        super(String.format("Unable to find user by id: %s", id));
    }
}
