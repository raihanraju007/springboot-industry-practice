package com.raju.enterprise.springboot_industry_practice.exception;

/**
 * Thrown when a requested resource does not exist. Maps to HTTP 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}