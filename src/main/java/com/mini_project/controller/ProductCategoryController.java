package com.mini_project.controller;

import com.mini_project.model.ProductCategory;
import com.mini_project.service.interfaces.ProductCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;


    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        try {
            List<ProductCategory> categories = productCategoryService.getAllCategories();

            if (categories == null || categories.isEmpty()) {
                log.warn("No categories found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            log.error("Error retrieving categories", e);
            throw new RuntimeException("Error retrieving categories: " + e.getMessage());
        }
    }

    @PostMapping("/category")
    public ResponseEntity<ProductCategory> createCategory(ProductCategory category) {
        try {
            ProductCategory newCategory = productCategoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        } catch (Exception e) {
            log.error("Error creating category", e);
            throw new RuntimeException("Error creating category: " + e.getMessage());
        }
    }

    @PostMapping("/category/update")
    public ResponseEntity<ProductCategory> updateCategory(ProductCategory category) {
        try {
            ProductCategory updatedCategory = productCategoryService.updateCategory(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            log.error("Error updating category", e);
            throw new RuntimeException("Error updating category: " + e.getMessage());
        }
    }

    @DeleteMapping("/category/delete")
    public ResponseEntity<String> deleteCategory(Long categoryId) {
        try {
            String message = productCategoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            log.error("Error deleting category", e);
            throw new RuntimeException("Error deleting category: " + e.getMessage());
        }
    }
}
