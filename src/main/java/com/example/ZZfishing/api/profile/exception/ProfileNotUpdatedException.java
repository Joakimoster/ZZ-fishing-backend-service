package com.example.ZZfishing.api.profile.exception;

import com.example.ZZfishing.model.exception.NotUpdatedException;

public class ProfileNotUpdatedException extends NotUpdatedException {

    public ProfileNotUpdatedException(long id) {
        super(String.format("Unable to update profile by id: %s", id));
    }
}
