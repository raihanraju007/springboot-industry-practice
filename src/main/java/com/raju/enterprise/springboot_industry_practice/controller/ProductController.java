package com.raju.enterprise.springboot_industry_practice.controller;

import com.raju.enterprise.springboot_industry_practice.constant.message.ProductMessage;
import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.product.UpdateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<APIResponse<List<ProductResponseDTO>>> getAllProducts() {
        return APIResponse.build(ProductMessage.LIST_FETCHED, productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        return APIResponse.build(ProductMessage.FETCHED, productService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<APIResponse<ProductResponseDTO>> createProduct(
            @RequestBody @Valid CreateProductRequestDTO productDto) {
        return APIResponse.build(ProductMessage.CREATED, productService.create(productDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<ProductResponseDTO>> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductRequestDTO productDto) {
        return APIResponse.build(ProductMessage.UPDATED, productService.update(id, productDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return APIResponse.build(ProductMessage.DELETED, "Deleted product id: " + id);
    }
}