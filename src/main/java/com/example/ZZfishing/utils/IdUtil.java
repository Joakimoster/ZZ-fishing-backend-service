package com.example.ZZfishing.utils;

import com.example.ZZfishing.model.exception.IdNotValidException;

public class IdUtil {

    public static void assertId(long id) throws IdNotValidException {
        if (!isValid(id)) {
            throw new IdNotValidException(id);
        }
    }

    public static boolean isValid(long id) {
        return id > 0;
    }
}
