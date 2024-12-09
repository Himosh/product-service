package com.mini_project.service;


import com.mini_project.model.ProductCategory;
import com.mini_project.repository.ProductCategoryRepository;
import com.mini_project.service.interfaces.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

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

    @Override
    public ProductCategory createCategory(ProductCategory category) {
        try {
            ProductCategory newCategory = productCategoryRepository.save(category);
            return newCategory;
        } catch (Exception e) {
            throw new RuntimeException("Error creating category: " + e.getMessage(), e);
        }
    }

    @Override
    public ProductCategory updateCategory(ProductCategory category) {
        try {
            ProductCategory updatedCategory = productCategoryRepository.save(category);
            return updatedCategory;
        } catch (Exception e) {
            throw new RuntimeException("Error updating category: " + e.getMessage(), e);
        }
    }

    @Override
    public String deleteCategory(Long categoryId) {
        try {
            productCategoryRepository.deleteById(categoryId);
            return "Category deleted successfully.";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting category: " + e.getMessage(), e);
        }
    }
}