package com.example.ZZfishing.api.profile.exception;

import com.example.ZZfishing.model.exception.CredentialsNotFoundException;

public class ProfileEmailTakenException extends CredentialsNotFoundException {

    public ProfileEmailTakenException(String email) {
        super(String.format(
                "Unable to create new profile with email address: %s",
                email + ". Please try with another email address!"));
    }
}
