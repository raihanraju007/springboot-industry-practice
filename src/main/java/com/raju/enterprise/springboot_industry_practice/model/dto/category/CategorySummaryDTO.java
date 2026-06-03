package com.raju.enterprise.springboot_industry_practice.model.dto.category;

import com.raju.enterprise.springboot_industry_practice.model.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Lightweight view of a Category, embedded inside other responses
 * (e.g. ProductResponseDTO) where only the essentials are needed —
 * not the full CategoryResponseDTO with timestamps.
 */
@Getter
@Setter
public class CategorySummaryDTO {
    private Long id;
    private String name;
    private CategoryStatus status;
}