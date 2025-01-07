package com.mini_project.service.interfaces;

import com.mini_project.model.dto.UpdateProductStatusDTO;
import com.mini_project.model.ProductCatalogRequest;
import com.mini_project.model.dto.ProductCatalogRequestDTO;
import com.mini_project.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCatalogRequestService {
    Page<ProductCatalogRequest> getAllProductCatalogRequests(Pageable pageable);
    List<ProductDTO> updateProductStatus(List<UpdateProductStatusDTO> productCatalogRequests);
    List<ProductCatalogRequest> createProductCatalogRequests(List<ProductCatalogRequestDTO> productCatalogRequests);
}