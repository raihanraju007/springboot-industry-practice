package com.raju.enterprise.springboot_industry_practice.model.dto.category;

import com.raju.enterprise.springboot_industry_practice.model.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}