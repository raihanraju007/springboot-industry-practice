package com.raju.enterprise.springboot_industry_practice.constant.message;

import com.raju.enterprise.springboot_industry_practice.helper.ApiStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Single source of truth for category response status + message.
 */
@Getter
@RequiredArgsConstructor
public enum CategoryMessage implements ApiStatus {

    CREATED(HttpStatus.CREATED, "Category created successfully"),
    FETCHED(HttpStatus.OK, "Category fetched successfully"),
    LIST_FETCHED(HttpStatus.OK, "Categories fetched successfully"),
    UPDATED(HttpStatus.OK, "Category updated successfully"),
    DELETED(HttpStatus.OK, "Category deleted successfully");

    private final HttpStatus status;
    private final String message;
}