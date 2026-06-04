package com.raju.enterprise.springboot_industry_practice.service;

import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    ResponseEntity<APIResponse<CategoryResponseDTO>> create(CreateCategoryRequestDTO dto);

    ResponseEntity<APIResponse<CategoryResponseDTO>> getById(Long id);

    ResponseEntity<APIResponse<List<CategoryResponseDTO>>> getAll();

    ResponseEntity<APIResponse<CategoryResponseDTO>> update(Long id, UpdateCategoryRequestDTO dto);

    ResponseEntity<APIResponse<String>> delete(Long id);
}
