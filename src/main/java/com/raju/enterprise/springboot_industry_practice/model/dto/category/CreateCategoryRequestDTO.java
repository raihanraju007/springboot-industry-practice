package com.raju.enterprise.springboot_industry_practice.model.dto.category;

import com.raju.enterprise.springboot_industry_practice.model.enums.CategoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    // Optional on create — defaults to ACTIVE if omitted.
    // Jackson maps the JSON string ("ACTIVE"/"INACTIVE") to the enum automatically.
    private CategoryStatus status;
}
