package com.ascending.demo.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringUtil {
    public static String convertObjectToJsonString (final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }
}
