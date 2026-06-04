package com.raju.enterprise.springboot_industry_practice.exception;

import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * One place that turns exceptions thrown anywhere in the app into a clean,
 * consistent JSON response. Without this, Spring returns its default error
 * page / stack-trace shape, and every controller would need its own try/catch.
 *
 * Handlers are matched most-specific-first, so ResourceNotFoundException is
 * caught by its own handler even though it is also an Exception.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 404 - something was requested by id/name but does not exist. */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error(ex.getMessage()));
    }

    /** 409 - tried to create a resource that breaks a uniqueness rule. */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<APIResponse<Object>> handleDuplicate(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(APIResponse.error(ex.getMessage()));
    }

    /** 400 - bad client input that we validate ourselves (e.g. invalid sort field). */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(APIResponse.error(ex.getMessage()));
    }

    /** 400 - bean validation (@Valid) failures, returned as field -> message. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() == null ? "Invalid value" : error.getDefaultMessage(),
                        (existing, replacement) -> existing,
                        HashMap::new));
        return ResponseEntity.badRequest().body(new APIResponse<>(false, "Validation failed", errors));
    }

    /** 500 - catch-all for anything we did not anticipate. Log the real cause,
     *  but never leak internal details (stack traces, SQL, etc.) to the client. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleUnexpected(Exception ex) {
        log.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponse.error("Something went wrong. Please try again later."));
    }

}