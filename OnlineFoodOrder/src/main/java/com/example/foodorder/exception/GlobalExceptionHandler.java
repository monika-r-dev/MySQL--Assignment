package com.example.foodorder.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom exception
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFound(
            OrderNotFoundException exception) {

        Map<String, String> error = new HashMap<>();

        error.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // Handle model validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                    errors.put(
                        error.getField(),
                        error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    // Handle request parameter validation errors
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleParameterValidation(
            HandlerMethodValidationException exception) {

        Map<String, String> error = new HashMap<>();

        error.put("error", "Invalid request parameter");

        return ResponseEntity.badRequest().body(error);
    }
}