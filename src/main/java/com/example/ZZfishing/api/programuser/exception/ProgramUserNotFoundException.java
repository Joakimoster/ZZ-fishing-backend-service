package com.example.ZZfishing.api.programuser.exception;

import com.example.ZZfishing.model.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProgramUserNotFoundException extends NotFoundException {

    public ProgramUserNotFoundException(Long id) {
        super(String.format("Unable to find profile by id: %s", id));
    }
}
