package com.mini_project.productservice.productservice.repository;

import com.mini_project.productservice.productservice.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
}
