package com.example.ZZfishing.api.profile.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class ProfileNotDeletedException extends NotFoundException {

    public ProfileNotDeletedException(long id) {
        super(String.format("Unable to delete profile by id: %s", id));
    }
}
