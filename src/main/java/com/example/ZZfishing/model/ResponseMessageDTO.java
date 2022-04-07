package com.example.ZZfishing.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseMessageDTO<T> {

    private boolean success;
    private int statusCode;
    private String Message;

    @JsonInclude
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
