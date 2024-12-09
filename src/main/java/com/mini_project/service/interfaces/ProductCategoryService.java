package com.mini_project.service.interfaces;

import com.mini_project.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory createCategory(ProductCategory category);
    ProductCategory updateCategory(ProductCategory category);
    String deleteCategory(Long categoryId);
}