package com.mini_project.service.interfaces;

import com.mini_project.model.dto.UpdateProductStatusDTO;
import com.mini_project.model.ProductCatalogRequest;
import com.mini_project.model.dto.ProductCatalogRequestDTO;
import com.mini_project.model.dto.ProductDTO;

import java.util.List;

public interface ProductCatalogRequestService {
    List<ProductDTO> updateProductStatus(List<UpdateProductStatusDTO> productCatalogRequests);
    List<ProductCatalogRequest> createProductCatalogRequests(List<ProductCatalogRequestDTO> productCatalogRequests);
}