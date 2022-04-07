package com.example.ZZfishing.api.profile.exception;

import com.example.ZZfishing.model.exception.NotFoundException;

public class ProfileNotFoundException extends NotFoundException {

    public ProfileNotFoundException(long id) {
        super(String.format("Unable to find profile by id: %s", id));
    }
}
