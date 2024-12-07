package com.mini_project.productservice.productservice.service.interfaces;

import com.mini_project.productservice.productservice.model.ProductCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory createCategory(ProductCategory category);
    ProductCategory updateCategory(ProductCategory category);
    String deleteCategory(Long categoryId);
}