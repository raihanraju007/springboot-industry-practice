package com.raju.enterprise.springboot_industry_practice.service;

import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO create(CreateCategoryRequestDTO dto);

    CategoryResponseDTO getById(Long id);

    List<CategoryResponseDTO> getAll();

    CategoryResponseDTO update(Long id, UpdateCategoryRequestDTO dto);

    void delete(Long id);
}