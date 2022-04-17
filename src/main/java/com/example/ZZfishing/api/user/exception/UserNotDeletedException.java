package com.example.ZZfishing.api.user.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class UserNotDeletedException extends NotFoundException {

    public UserNotDeletedException(long id) {
        super(String.format("Unable to find user by id: %s", id));
    }
}
