package com.mini_project.controller;

import com.mini_project.model.ProductCategory;
import com.mini_project.model.dto.ProductCategoryDTO;
import com.mini_project.service.interfaces.ProductCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductCategoryController extends AbstractBaseController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        return handleCustomResponse(
                () -> {
                    List<ProductCategory> categories = productCategoryService.getAllCategories();
                    if (categories == null || categories.isEmpty()) {
                        log.warn("No categories found.");
                    }
                    return categories;
                },
                HttpStatus.OK,
                "Retrieving all product categories"
        );
    }

    @PostMapping("/category")
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategoryDTO categoryDTO) {
        return handleCustomResponse(
                () -> productCategoryService.createCategory(categoryDTO),
                HttpStatus.CREATED,
                "Creating a new product category"
        );
    }

    @PostMapping("/category/update")
    public ResponseEntity<ProductCategory> updateCategory(@RequestBody ProductCategory category) {
        return handleCustomResponse(
                () -> productCategoryService.updateCategory(category),
                HttpStatus.OK,
                "Updating a product category"
        );
    }

    @DeleteMapping("/category/delete")
    public ResponseEntity<String> deleteCategory(@RequestParam Long categoryId) {
        return handleCustomResponse(
                () -> productCategoryService.deleteCategory(categoryId),
                HttpStatus.OK,
                "Deleting product category with ID: " + categoryId
        );
    }
}
