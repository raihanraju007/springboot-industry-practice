package com.raju.enterprise.springboot_industry_practice.mapper;

import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setStatus(dto.getStatus());   // null is fine -> entity defaults to ACTIVE
        return category;
    }

    public CategoryResponseDTO toResponse(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setStatus(category.getStatus());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());
        return dto;
    }

    public void updateEntity(Category category, UpdateCategoryRequestDTO dto) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setStatus(dto.getStatus());
    }
}