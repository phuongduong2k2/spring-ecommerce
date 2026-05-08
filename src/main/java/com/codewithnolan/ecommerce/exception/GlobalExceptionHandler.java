package com.codewithnolan.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError
                .Builder()
                .withMessage(ex.getMessage())
                .withHttpStatus(HttpStatus.NOT_FOUND)
                .withCreatedAt()
                .build();
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        ApiError apiError = new ApiError
                .Builder()
                .withMessage("Some Error Occurred")
                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withCreatedAt()
                .build();
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorDetail = String.format("Invalid %s", ex.getName());
        
        ApiError apiError = new ApiError
                .Builder()
                .withMessage(errorDetail)
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withCreatedAt()
                .build();
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorDetail = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ApiError apiError = new ApiError
                .Builder()
                .withMessage(errorDetail)
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withCreatedAt()
                .build();
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}
