package com.example.ZZfishing.api.profile.exception;

import com.example.ZZfishing.model.exception.CredentialsFoundException;

public class ProfileEmailTakenException extends CredentialsFoundException {

    public ProfileEmailTakenException(String email) {
        super(String.format(
                "Unable to create new profile with email address: %s",
                email + ". Please try with another email address!"));
    }
}
