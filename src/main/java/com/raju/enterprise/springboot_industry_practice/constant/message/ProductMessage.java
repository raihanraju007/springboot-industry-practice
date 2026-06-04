package com.raju.enterprise.springboot_industry_practice.constant.message;

import com.raju.enterprise.springboot_industry_practice.helper.ApiStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Single source of truth for product response status + message.
 */
@Getter
@RequiredArgsConstructor
public enum ProductMessage implements ApiStatus {

    CREATED(HttpStatus.CREATED, "Product created successfully"),
    FETCHED(HttpStatus.OK, "Product fetched successfully"),
    LIST_FETCHED(HttpStatus.OK, "Products fetched successfully"),
    UPDATED(HttpStatus.OK, "Product updated successfully"),
    DELETED(HttpStatus.OK, "Product deleted successfully");

    private final HttpStatus status;
    private final String message;
}