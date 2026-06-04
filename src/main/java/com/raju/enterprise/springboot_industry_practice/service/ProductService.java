package com.raju.enterprise.springboot_industry_practice.service;

import com.raju.enterprise.springboot_industry_practice.model.dto.product.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.UpdateProductRequestDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO create(CreateProductRequestDTO dto);

    ProductResponseDTO getById(Long id);

    List<ProductResponseDTO> getAll();

    ProductResponseDTO update(Long id, UpdateProductRequestDTO dto);

    void delete(Long id);
}
