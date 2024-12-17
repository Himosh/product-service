package com.mini_project.controller;

import com.mini_project.model.ProductCatalogRequest;
import com.mini_project.model.dto.ProductCatalogRequestDTO;
import com.mini_project.model.dto.UpdateProductStatusDTO;
import com.mini_project.model.dto.ProductDTO;
import com.mini_project.service.ProductServiceImpl;
import com.mini_project.service.ProductCatalogRequestServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController extends AbstractBaseController {

    private final ProductCatalogRequestServiceImpl productUpdateService;
    private final ProductServiceImpl productService;

    @Autowired
    private ProductCatalogRequestServiceImpl productCatalogRequestService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return handlePaginatedResponse(
                () -> productService.getAllProducts(PageRequest.of(page, size)),
                "Retrieving all products"
        );
    }

    @GetMapping("/category")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(
            @RequestParam String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return handlePaginatedResponse(
                () -> productService.getProductsByCategory(category, PageRequest.of(page, size)),
                "Retrieving products by category"
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        return handleSingleResponse(
                () -> productService.getProductById(productId),
                "Fetching product by ID: " + productId
        );
    }

    @GetMapping("/search-by-category-id")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategoryId(
            @RequestParam Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return handlePaginatedResponse(
                () -> productService.getAllProductsByCategory(categoryId, PageRequest.of(page, size)),
                "Retrieving products by category ID"
        );
    }

    @PostMapping("/create-catalog-request")
    public ResponseEntity<List<ProductCatalogRequest>> createProductCatalogRequest(
            @RequestBody List<ProductCatalogRequestDTO> productCatalogRequestDTO) {
        return handleCustomResponse(
                () -> productCatalogRequestService.createProductCatalogRequests(productCatalogRequestDTO),
                HttpStatus.CREATED,
                "Creating product catalog requests"
        );
    }

    @PutMapping("/update-product-status")
    public ResponseEntity<List<ProductDTO>> updateProductStatus(
            @RequestBody List<UpdateProductStatusDTO> updateProductStatusDTOList) {
        return handleCustomResponse(
                () -> productCatalogRequestService.updateProductStatus(updateProductStatusDTOList),
                HttpStatus.OK,
                "Updating product statuses"
        );
    }
}
