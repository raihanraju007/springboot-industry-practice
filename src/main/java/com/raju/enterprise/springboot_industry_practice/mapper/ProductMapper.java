package com.raju.enterprise.springboot_industry_practice.mapper;

import com.raju.enterprise.springboot_industry_practice.model.dto.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.UpdateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(CreateProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }

    public ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());

        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public void updateEntity(Product product, UpdateProductRequestDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
    }
}
