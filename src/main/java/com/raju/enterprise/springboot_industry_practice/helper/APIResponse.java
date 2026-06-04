package com.raju.enterprise.springboot_industry_practice.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class APIResponse<T> {

    private boolean success;
    private int statusCode;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public APIResponse(boolean success, int statusCode, String message, T data) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Builds a success response from a status-dictionary entry. The HTTP status
     * on the response and the {@code statusCode} in the body come from the same
     * {@link ApiStatus}, so they can never drift apart.
     */
    public static <T> ResponseEntity<APIResponse<T>> build(ApiStatus status, T data) {
        APIResponse<T> body = new APIResponse<>(true, status.getStatus().value(), status.getMessage(), data);
        return ResponseEntity.status(status.getStatus()).body(body);
    }

    public static <T> APIResponse<T> error(HttpStatus status, String message) {
        return new APIResponse<>(false, status.value(), message, null);
    }
}
