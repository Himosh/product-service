package com.mini_project.productservice.productservice.repository;

import com.mini_project.productservice.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product findByProductId(String productId);
    Page<Product> findByCategory_CategoryName(String categoryName, Pageable pageable);

    Page<Product> findBySupplierId(String supplierId, Pageable pageable);

    Page<Product> findByCategoryContainingIgnoreCase(String categoryName, Pageable pageable);

    @Query(value = "SELECT * FROM products p WHERE p.name LIKE %:productName%", nativeQuery = true)
    Page<Product> searchByPartialName(@Param("productName") String productName, Pageable pageable);

    @Query(value = "SELECT p.* FROM products p INNER JOIN product_category c ON p.category_name = c.category_name WHERE c.category_name LIKE %:categoryName%", nativeQuery = true)
    Page<Product> searchByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);
}
