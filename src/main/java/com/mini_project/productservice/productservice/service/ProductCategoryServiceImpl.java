package com.mini_project.productservice.productservice.service;


import com.mini_project.productservice.productservice.model.ProductCategory;
import com.mini_project.productservice.productservice.repository.ProductCategoryRepository;
import com.mini_project.productservice.productservice.service.interfaces.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;


    /**
     * Retrieve all categories from the database.
     *
     * @return List of ProductCategory objects
     * @throws RuntimeException if an error occurs during fetching categories
     */
    @Override
    public List<ProductCategory> getAllCategories() {
        try {
            List<ProductCategory> categories = productCategoryRepository.findAll();
            if (categories.isEmpty()) {
                throw new RuntimeException("No categories found.");
            }
            return categories;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving categories: " + e.getMessage(), e);
        }
    }
}