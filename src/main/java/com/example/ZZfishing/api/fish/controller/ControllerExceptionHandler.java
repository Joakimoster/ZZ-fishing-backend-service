package com.example.ZZfishing.api.fish.controller;

import com.example.ZZfishing.api.fish.exception.FishNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Map<String, String> handleException(Exception ex) {
        return Map.of("status", "failed", "error", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(FishNotFoundException.class)
    public void handleNotFound(FishNotFoundException ex) {
        log.error("Requested account not found");
    }
}
