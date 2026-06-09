package com.raju.enterprise.springboot_industry_practice.controller;

import com.raju.enterprise.springboot_industry_practice.constant.message.CategoryMessage;
import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "CRUD operations for product categories")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "List all categories", description = "Returns every category in the system")
    @GetMapping("/list")
    public ResponseEntity<APIResponse<List<CategoryResponseDTO>>> getAllCategories() {
        return APIResponse.build(CategoryMessage.LIST_FETCHED, categoryService.getAll());
    }

    @Operation(summary = "Get a category by ID", description = "Returns a single category, or 404 if not found")
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
        return APIResponse.build(CategoryMessage.FETCHED, categoryService.getById(id));
    }

    @Operation(summary = "Create a category", description = "Creates a new category; status defaults to ACTIVE if omitted")
    @PostMapping("/save")
    public ResponseEntity<APIResponse<CategoryResponseDTO>> createCategory(
            @RequestBody @Valid CreateCategoryRequestDTO categoryDto) {
        return APIResponse.build(CategoryMessage.CREATED, categoryService.create(categoryDto));
    }

    @Operation(summary = "Update a category", description = "Updates an existing category by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCategoryRequestDTO categoryDto) {
        return APIResponse.build(CategoryMessage.UPDATED, categoryService.update(id, categoryDto));
    }

    @Operation(summary = "Delete a category", description = "Deletes a category by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return APIResponse.build(CategoryMessage.DELETED, "Deleted category id: " + id);
    }
}