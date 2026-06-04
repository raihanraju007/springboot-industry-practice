package com.raju.enterprise.springboot_industry_practice.exception;

/**
 * Thrown when creating a resource that violates a uniqueness rule
 * (e.g. a category name that already exists). Maps to HTTP 409 Conflict.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}