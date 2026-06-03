package com.raju.enterprise.springboot_industry_practice.model.enums;

/**
 * Lifecycle status of a Category.
 * Stored in the DB as its name ("ACTIVE" / "INACTIVE") via @Enumerated(EnumType.STRING),
 * which is the safe choice — ORDINAL would break if the enum order ever changes.
 */
public enum CategoryStatus {
    ACTIVE,
    INACTIVE
}