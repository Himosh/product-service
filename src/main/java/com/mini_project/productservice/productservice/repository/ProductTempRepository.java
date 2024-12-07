package com.mini_project.productservice.productservice.repository;

import com.mini_project.productservice.productservice.model.ProductCatalogRequest;
import com.mini_project.productservice.productservice.model.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTempRepository extends JpaRepository<ProductCatalogRequest,Long> {
    List<ProductCatalogRequest> findByStatusAndSupplierId(ProductStatus status, String supplierId);

    void deleteByProductId(String productId);
}
