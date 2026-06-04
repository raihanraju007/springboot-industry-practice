package com.raju.enterprise.springboot_industry_practice.service.impl;

import com.raju.enterprise.springboot_industry_practice.exception.ResourceNotFoundException;
import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.mapper.ProductMapper;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.UpdateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Category;
import com.raju.enterprise.springboot_industry_practice.model.entity.Product;
import com.raju.enterprise.springboot_industry_practice.repository.CategoryRepository;
import com.raju.enterprise.springboot_industry_practice.repository.ProductRepository;
import com.raju.enterprise.springboot_industry_practice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository,
                              CategoryRepository categoryRepository,
                              ProductMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<ProductResponseDTO>> create(CreateProductRequestDTO dto) {
        Category category = findCategoryOrThrow(dto.getCategoryId());
        Product product = mapper.toEntity(dto, category);
        ProductResponseDTO saved = mapper.toResponse(repository.save(product));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(APIResponse.success("Product created successfully", saved));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<APIResponse<ProductResponseDTO>> getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(APIResponse.success("Product fetched successfully", mapper.toResponse(product)));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<APIResponse<List<ProductResponseDTO>>> getAll() {
        List<ProductResponseDTO> products = repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(APIResponse.success("Products fetched successfully", products));
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<ProductResponseDTO>> update(Long id, UpdateProductRequestDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = findCategoryOrThrow(dto.getCategoryId());
        mapper.updateEntity(product, dto, category);
        ProductResponseDTO updated = mapper.toResponse(repository.save(product));
        return ResponseEntity.ok(APIResponse.success("Product updated successfully", updated));
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<String>> delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        repository.delete(product);
        return ResponseEntity.ok(APIResponse.success("Product deleted successfully", "Deleted product id: " + id));
    }

    private Category findCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
    }
}
