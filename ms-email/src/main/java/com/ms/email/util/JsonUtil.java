package com.ms.email.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;

public class JsonUtil {
    private static ObjectMapper objectMapper;
    private JsonUtil() {

    }



    public static <T> T convertString(@NotNull final String json, Class<T> classToConvert) throws JsonProcessingException {
         objectMapper = new ObjectMapper();
         objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.readValue(json, classToConvert);
    }
}
