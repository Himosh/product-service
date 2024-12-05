package com.mini_project.productservice.productservice.repository;

import com.mini_project.productservice.productservice.model.ProductTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTempRepository extends JpaRepository<ProductTemp,Long> {
    List<ProductTemp> findByStatusAndSupplierId(ProductTemp.Status status, String supplierId);

    void deleteByProductId(String productId);
}
