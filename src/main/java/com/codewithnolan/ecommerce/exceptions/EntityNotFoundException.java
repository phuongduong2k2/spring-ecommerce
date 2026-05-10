package com.codewithnolan.ecommerce.exceptions;

import lombok.Setter;

@Setter
public class EntityNotFoundException extends RuntimeException {
    private String message;

    public EntityNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
