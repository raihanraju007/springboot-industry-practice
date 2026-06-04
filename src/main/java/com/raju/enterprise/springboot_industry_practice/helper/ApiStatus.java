package com.raju.enterprise.springboot_industry_practice.helper;

import org.springframework.http.HttpStatus;

/**
 * Implemented by the per-resource message enums so {@link APIResponse#build}
 * can read the HTTP status and message from a single source.
 */
public interface ApiStatus {

    HttpStatus getStatus();

    String getMessage();
}