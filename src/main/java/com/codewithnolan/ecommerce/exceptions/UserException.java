package com.codewithnolan.ecommerce.exceptions;

import lombok.Setter;

@Setter
public class UserException extends RuntimeException {
    private String message;

    public UserException(String message) { this.message = message; };

    @Override
    public String getMessage() {
        return message;
    }

}
