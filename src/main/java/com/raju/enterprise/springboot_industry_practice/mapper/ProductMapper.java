package com.raju.enterprise.springboot_industry_practice.mapper;

import com.raju.enterprise.springboot_industry_practice.model.dto.product.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.UpdateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.entity.Category;
import com.raju.enterprise.springboot_industry_practice.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // Category is resolved in the service (it owns the repositories) and passed in here.
    public Product toEntity(CreateProductRequestDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        return product;
    }

    public ProductResponseDTO toResponse(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public void updateEntity(Product product, UpdateProductRequestDTO dto, Category category) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
    }
}