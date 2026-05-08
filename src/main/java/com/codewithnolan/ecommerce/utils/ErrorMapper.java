package com.codewithnolan.ecommerce.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ErrorMapper {

    /**
     * Creates map with key: "message" and value: exception's message.
     *
     * @param e - the thrown exception
     * @return the created map
     */
    public Map<String, String> createErrorMap(ResponseStatusException e) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", e.getReason());
        return errorMsg;
    }

    public Map<String, String> createErrorMap(String message) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", message);

        return errorMsg;
    }
}
