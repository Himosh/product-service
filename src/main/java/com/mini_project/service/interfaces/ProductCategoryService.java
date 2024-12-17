package com.mini_project.service.interfaces;

import com.mini_project.model.ProductCategory;
import com.mini_project.model.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory createCategory(ProductCategoryDTO category);
    ProductCategory updateCategory(ProductCategory category);
    String deleteCategory(Long categoryId);
}