package com.codewithnolan.ecommerce.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private String message;
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    public ApiError(Builder builder) {
        this.message = builder.message;
        this.httpStatus = builder.httpStatus;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private String message;
        private HttpStatus httpStatus;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime createdAt;

        public Builder() {}

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder withCreatedAt() {
            this.createdAt = LocalDateTime.now();
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
