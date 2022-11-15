package com.acem.db.utils;

import com.acem.db.exception.ExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JacksonUtil {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String toJson(Object object) {
        return ExceptionHandler
                .handleReturnableWithFallBack(
                        () -> objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object),
                        null
                );
    }

    public static <T> T toObject(InputStream inputStream, Class<T> clazz) {
        return ExceptionHandler
                .handleReturnableWithFallBack(
                        () -> objectMapper.readValue(inputStream, clazz),
                        null);
    }
}
