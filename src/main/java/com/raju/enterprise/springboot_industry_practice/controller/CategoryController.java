package com.raju.enterprise.springboot_industry_practice.controller;

import com.raju.enterprise.springboot_industry_practice.constant.APIRouteList;
import com.raju.enterprise.springboot_industry_practice.helper.APIResponse;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CategoryResponseDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.CreateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.model.dto.category.UpdateCategoryRequestDTO;
import com.raju.enterprise.springboot_industry_practice.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = APIRouteList.CATEGORY_LIST, method = RequestMethod.GET)
    public ResponseEntity<APIResponse<Page<CategoryResponseDTO>>> getAllCategories(
            @PageableDefault(sort = "id") Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @RequestMapping(value = APIRouteList.CATEGORY_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<APIResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @RequestMapping(value = APIRouteList.CATEGORY_SAVE, method = RequestMethod.POST)
    public ResponseEntity<APIResponse<CategoryResponseDTO>> createCategory(
            @RequestBody @Valid CreateCategoryRequestDTO categoryDto) {
        return categoryService.create(categoryDto);
    }

    @RequestMapping(value = APIRouteList.CATEGORY_UPDATE, method = RequestMethod.PUT)
    public ResponseEntity<APIResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCategoryRequestDTO categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @RequestMapping(value = APIRouteList.CATEGORY_DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse<String>> deleteCategory(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}