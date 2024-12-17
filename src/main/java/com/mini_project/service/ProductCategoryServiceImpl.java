package com.mini_project.service;


import com.mini_project.model.ProductCategory;
import com.mini_project.model.dto.ProductCategoryDTO;
import com.mini_project.repository.ProductCategoryRepository;
import com.mini_project.service.interfaces.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public ProductCategory createCategory(ProductCategoryDTO category) {
        try {
            ProductCategory newCategory = modelMapper.map(category, ProductCategory.class);
            return productCategoryRepository.save(newCategory);
        } catch (Exception e) {
            throw new RuntimeException("Error creating category: " + e.getMessage(), e);
        }
    }

    @Override
    public ProductCategory updateCategory(ProductCategory category) {
        try {
            return productCategoryRepository.save(category);
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