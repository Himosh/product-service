package com.mini_project.controller;

import com.mini_project.model.ProductCatalogRequest;
import com.mini_project.model.dto.ProductCatalogRequestDTO;
import com.mini_project.model.dto.UpdateProductStatusDTO;
import com.mini_project.model.dto.ProductDTO;
import com.mini_project.service.ProductServiceImpl;
import com.mini_project.service.ProductCatalogRequestServiceImpl;
import com.mini_project.service.interfaces.ProductCatalogRequestService;
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
public class ProductController {

    private final ProductCatalogRequestServiceImpl productUpdateService;
    private final ProductServiceImpl productService;

    @Autowired
    private ProductCatalogRequestService productCatalogRequestService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<ProductDTO> products = productService.getAllProducts(PageRequest.of(page, size));
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error retrieving products", e);
            throw new RuntimeException("Error retrieving products: " + e.getMessage(), e);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(
            @RequestParam String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<ProductDTO> products = productService.getProductsByCategory(category, PageRequest.of(page, size));
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Page.empty());
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error retrieving products by category", e);
            throw new RuntimeException("Error retrieving products by category: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        try {
            ProductDTO product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            log.warn("Product not found for ID: {}", productId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error retrieving product by ID", e);
            throw new RuntimeException("Error retrieving product by ID: " + e.getMessage(), e);
        }
    }

    @GetMapping("/search-by-product-name")
    public ResponseEntity<Page<ProductDTO>> searchProductsByProductName(
            @RequestParam String productName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<ProductDTO> products = productService.searchProductsByProductName(productName, PageRequest.of(page, size));
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Page.empty());
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error searching products by name", e);
            throw new RuntimeException("Error searching products by name: " + e.getMessage(), e);
        }
    }

    @PostMapping("/create-catalog-request")
    public ResponseEntity<List<ProductCatalogRequest>> createProductCatalogRequest(@RequestBody List<ProductCatalogRequestDTO> productCatalogRequestDTO) {
        try {
            log.info("Creating Product Catalog Request");
            List<ProductCatalogRequest> productCatalogRequests = productCatalogRequestService.createProductCatalogRequests(productCatalogRequestDTO);
            return new ResponseEntity<>(productCatalogRequests, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create Product Catalog Request Failed", e);
            throw new RuntimeException("Create Product Catalog Request Failed" + e.getMessage());
        }
    }

    @PutMapping("/update-product-status")
    public ResponseEntity<List<ProductDTO>> updateProductStatus(@RequestBody List<UpdateProductStatusDTO> updateProductStatusDTOList) {
        try {
            log.info("Updating Product Status");
            List<ProductDTO> productDTOList = productCatalogRequestService.updateProductStatus(updateProductStatusDTOList);
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update Product Status Failed", e);
            throw new RuntimeException("Update Product Status Failed" + e.getMessage());
        }
    }
}
