package com.raju.enterprise.springboot_industry_practice.service.impl;

import com.raju.enterprise.springboot_industry_practice.exception.DuplicateResourceException;
import com.raju.enterprise.springboot_industry_practice.exception.ResourceNotFoundException;
import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.mapper.CategoryMapper;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Category;
import com.raju.enterprise.springboot_industry_practice.repository.CategoryRepository;
import com.raju.enterprise.springboot_industry_practice.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<APIResponse<CategoryResponseDTO>> create(CreateCategoryRequestDTO dto) {
        if (repository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("Category with name '" + dto.getName() + "' already exists");
        }
        Category category = mapper.toEntity(dto);
        CategoryResponseDTO saved = mapper.toResponse(repository.save(category));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(APIResponse.success("Category created successfully", saved));
    }

    @Override
    public ResponseEntity<APIResponse<CategoryResponseDTO>> getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return ResponseEntity.ok(APIResponse.success("Category fetched successfully", mapper.toResponse(category)));
    }

    @Override
    public ResponseEntity<APIResponse<Page<CategoryResponseDTO>>> getAll(Pageable pageable) {
        // Spring resolves the Pageable from ?page=&size=&sort= automatically,
        // and Page.map keeps all the paging metadata for us.
        Page<CategoryResponseDTO> categories = repository.findAll(pageable).map(mapper::toResponse);
        return ResponseEntity.ok(APIResponse.success("Categories fetched successfully", categories));
    }

    @Override
    public ResponseEntity<APIResponse<CategoryResponseDTO>> update(Long id, UpdateCategoryRequestDTO dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        mapper.updateEntity(category, dto);
        CategoryResponseDTO updated = mapper.toResponse(repository.save(category));
        return ResponseEntity.ok(APIResponse.success("Category updated successfully", updated));
    }

    @Override
    public ResponseEntity<APIResponse<String>> delete(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        repository.delete(category);
        return ResponseEntity.ok(APIResponse.success("Category deleted successfully", "Deleted category id: " + id));
    }
}