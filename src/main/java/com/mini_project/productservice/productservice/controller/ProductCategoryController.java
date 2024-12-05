package com.mini_project.productservice.productservice.controller;

import com.mini_project.productservice.productservice.model.ProductCategory;
import com.mini_project.productservice.productservice.service.interfaces.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        try {
            List<ProductCategory> categories = productCategoryService.getAllCategories();

            if (categories == null || categories.isEmpty()) {
                logger.warn("No categories found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            logger.error("Error retrieving categories", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
