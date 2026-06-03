package com.raju.enterprise.springboot_industry_practice.controller;

import com.raju.enterprise.springboot_industry_practice.constant.APIRouteList;
import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.helper.PagedResponse;
import com.raju.enterprise.springboot_industry_practice.model.dto.CreateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.ProductResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.UpdateProductRequestDTO;
import com.raju.enterprise.springboot_industry_practice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = APIRouteList.PRODUCT_LIST, method = RequestMethod.GET)
    public ResponseEntity<APIResponse<PagedResponse<ProductResponseDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        return productService.getAll(page, size, sort);
    }

    @RequestMapping(value = APIRouteList.PRODUCT_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<APIResponse<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @RequestMapping(value = APIRouteList.PRODUCT_SAVE, method = RequestMethod.POST)
    public ResponseEntity<APIResponse<ProductResponseDTO>> createProduct(
            @RequestBody @Valid CreateProductRequestDTO productDto) {
        return productService.create(productDto);
    }

    @RequestMapping(value = APIRouteList.PRODUCT_UPDATE, method = RequestMethod.PUT)
    public ResponseEntity<APIResponse<ProductResponseDTO>> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductRequestDTO productDto) {
        return productService.update(id, productDto);
    }

    @RequestMapping(value = APIRouteList.PRODUCT_DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse<String>> deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }
}