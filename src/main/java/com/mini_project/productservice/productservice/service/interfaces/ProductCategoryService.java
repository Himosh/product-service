package com.mini_project.productservice.productservice.service.interfaces;

import com.mini_project.productservice.productservice.model.ProductCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductCategoryService {

    /**
     * Retrieves all product categories from the database.
     *
     * @return a ResponseEntity with a list of all product categories.
     */
    List<ProductCategory> getAllCategories();

}