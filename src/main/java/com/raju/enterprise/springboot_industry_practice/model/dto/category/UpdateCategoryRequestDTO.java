package com.raju.enterprise.springboot_industry_practice.model.dto.category;

import com.raju.enterprise.springboot_industry_practice.model.enums.CategoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequestDTO {

    @Schema(description = "Category name", example = "Electronics", maxLength = 100)
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Schema(description = "Optional description", example = "Phones, laptops and accessories", maxLength = 500)
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Schema(description = "Category status", example = "ACTIVE", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Status is required")
    private CategoryStatus status;
}