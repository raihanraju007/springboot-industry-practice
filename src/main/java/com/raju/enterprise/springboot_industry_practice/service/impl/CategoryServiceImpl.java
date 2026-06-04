package com.raju.enterprise.springboot_industry_practice.service.impl;

import com.raju.enterprise.springboot_industry_practice.exception.DuplicateResourceException;
import com.raju.enterprise.springboot_industry_practice.exception.ResourceNotFoundException;
import com.raju.enterprise.springboot_industry_practice.mapper.CategoryMapper;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Category;
import com.raju.enterprise.springboot_industry_practice.repository.CategoryRepository;
import com.raju.enterprise.springboot_industry_practice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponseDTO create(CreateCategoryRequestDTO dto) {
        if (repository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("Category with name '" + dto.getName() + "' already exists");
        }
        Category category = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(category));
    }

    @Override
    public CategoryResponseDTO getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return mapper.toResponse(category);
    }

    @Override
    public List<CategoryResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponseDTO update(Long id, UpdateCategoryRequestDTO dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        mapper.updateEntity(category, dto);
        return mapper.toResponse(repository.save(category));
    }

    @Override
    public void delete(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        repository.delete(category);
    }
}