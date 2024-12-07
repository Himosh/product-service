package com.mini_project.productservice.productservice.controller;

import com.mini_project.productservice.productservice.model.dto.ProductCatalogAddRequest;
import com.mini_project.productservice.productservice.model.dto.ProductDTO;
import com.mini_project.productservice.productservice.service.ProductServiceImpl;
import com.mini_project.productservice.productservice.service.UpdateProductCatalogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final UpdateProductCatalogServiceImpl productUpdateService;
    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<ProductDTO> products = productService.getAllProducts(PageRequest.of(page, size));
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving products: " + e.getMessage());
        }
    }

    @GetMapping(params = "category")
    public ResponseEntity<?> getProductsByCategory(
            @RequestParam String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<ProductDTO> products = productService.getProductsByCategory(category, PageRequest.of(page, size));
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found in this category.");
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving products by category: " + e.getMessage());
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        try {
            ProductDTO product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving product by ID: " + e.getMessage());
        }
    }

    @GetMapping(params = "productName")
    public ResponseEntity<?> searchProductsByProductName(
            @RequestParam String productName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<ProductDTO> products = productService.searchProductsByProductName(productName, PageRequest.of(page, size));
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found with the given name.");
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error searching products by name: " + e.getMessage());
        }
    }

    @PatchMapping("/catalog")
    public ResponseEntity<?> submitChangeRequests(@RequestBody List<ProductCatalogAddRequest> requestList) {
        return productUpdateService.submitChangeRequest(requestList);
    }
}
