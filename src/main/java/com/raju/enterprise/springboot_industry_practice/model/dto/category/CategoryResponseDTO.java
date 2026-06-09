package com.raju.enterprise.springboot_industry_practice.model.dto.category;

import com.raju.enterprise.springboot_industry_practice.model.enums.CategoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponseDTO {

    @Schema(description = "Unique category ID", example = "1")
    private Long id;

    @Schema(description = "Category name", example = "Electronics")
    private String name;

    @Schema(description = "Category description", example = "Phones, laptops and accessories")
    private String description;

    @Schema(description = "Category status", example = "ACTIVE")
    private CategoryStatus status;

    @Schema(description = "When the category was created", example = "2026-06-09T13:55:21")
    private LocalDateTime createdAt;

    @Schema(description = "When the category was last updated", example = "2026-06-09T14:10:00")
    private LocalDateTime updatedAt;
}