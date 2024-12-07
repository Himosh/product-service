package com.mini_project.productservice.productservice.repository;

import com.mini_project.productservice.productservice.model.ProductCatalogRequest;
import com.mini_project.productservice.productservice.model.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCatalogRequestRepository extends JpaRepository<ProductCatalogRequest,Long> {
    List<ProductCatalogRequest> findByStatusAndSupplierId(RequestStatus status, String supplierId);

    void deleteByProductId(Long productId);
}
