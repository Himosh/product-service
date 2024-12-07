package com.mini_project.productservice.productservice.service.interfaces;

import com.mini_project.productservice.productservice.model.ProductCatalogRequest;
import com.mini_project.productservice.productservice.model.dto.ProductCatalogAddRequestDTO;
import com.mini_project.productservice.productservice.model.dto.ProductCatalogRequestDTO;
import com.mini_project.productservice.productservice.model.dto.ProductDTO;
import com.mini_project.productservice.productservice.model.dto.UpdateProductStatusDTO;

import java.util.List;

public interface ProductCatalogRequestService {
    List<ProductDTO> updateProductStatus(List<UpdateProductStatusDTO> productCatalogRequests);
    List<ProductCatalogRequest> createProductCatalogRequests(List<ProductCatalogRequestDTO> productCatalogRequests);
}