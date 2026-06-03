package com.raju.enterprise.springboot_industry_practice.service.impl;

import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.helper.PagedResponse;
import com.raju.enterprise.springboot_industry_practice.mapper.ProductMapper;
import com.raju.enterprise.springboot_industry_practice.model.dto.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.UpdateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Product;
import com.raju.enterprise.springboot_industry_practice.repository.ProductRepository;
import com.raju.enterprise.springboot_industry_practice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final int MAX_PAGE_SIZE = 100;
    private static final Set<String> ALLOWED_SORT_FIELDS =
            Set.of("id", "name", "price", "createdAt", "updatedAt");

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<APIResponse<ProductResponseDTO>> create(CreateProductRequestDTO dto) {
        Product product = mapper.toEntity(dto);
        ProductResponseDTO saved = mapper.toDTO(repository.save(product));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(APIResponse.success("Product created successfully", saved));
    }

    @Override
    public ResponseEntity<APIResponse<ProductResponseDTO>> getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok(APIResponse.success("Product fetched successfully", mapper.toDTO(product)));
    }

    @Override
    public ResponseEntity<APIResponse<PagedResponse<ProductResponseDTO>>> getAll(int page, int size, String sort) {
        int safePage = Math.max(page, 0);          // no negative pages
        int safeSize = Math.min(Math.max(size, 1), MAX_PAGE_SIZE);   // 1..MAX_PAGE_SIZE
        Pageable pageable = PageRequest.of(safePage, safeSize, parseSort(sort));

        Page<ProductResponseDTO> result = repository.findAll(pageable)
                .map(mapper::toDTO);               // Page.map keeps all the paging metadata

        PagedResponse<ProductResponseDTO> body = PagedResponse.from(result);
        return ResponseEntity.ok(APIResponse.success("Products fetched successfully", body));
    }

    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.DESC, "id");
        }
        String[] parts = sort.split(",");
        String field = parts[0].trim();
        if (!ALLOWED_SORT_FIELDS.contains(field)) {
            throw new RuntimeException("Invalid sort field: " + field
                    + ". Allowed: " + ALLOWED_SORT_FIELDS);
        }
        Sort.Direction direction = (parts.length > 1 && parts[1].trim().equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, field);
    }

    @Override
    public ResponseEntity<APIResponse<ProductResponseDTO>> update(Long id, UpdateProductRequestDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        mapper.updateEntity(product, dto);
        ProductResponseDTO updated = mapper.toDTO(repository.save(product));
        return ResponseEntity.ok(APIResponse.success("Product updated successfully", updated));
    }

    @Override
    public ResponseEntity<APIResponse<String>> delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        repository.delete(product);
        return ResponseEntity.ok(APIResponse.success("Product deleted successfully", "Deleted product id: " + id));
    }
}