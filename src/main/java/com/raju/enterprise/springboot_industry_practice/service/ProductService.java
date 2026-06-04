package com.raju.enterprise.springboot_industry_practice.service;

import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.UpdateProductRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<APIResponse<ProductResponseDTO>> create(CreateProductRequestDTO dto);

    ResponseEntity<APIResponse<ProductResponseDTO>> getById(Long id);

    ResponseEntity<APIResponse<List<ProductResponseDTO>>> getAll();

    ResponseEntity<APIResponse<ProductResponseDTO>> update(Long id, UpdateProductRequestDTO dto);

    ResponseEntity<APIResponse<String>> delete(Long id);
}