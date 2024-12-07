package com.mini_project.productservice.productservice.service;

import com.mini_project.productservice.productservice.model.dto.ProductCatalogAddRequest;
import com.mini_project.productservice.productservice.model.Product;
import com.mini_project.productservice.productservice.model.ProductCatalogRequest;
import com.mini_project.productservice.productservice.model.enums.ProductStatus;
import com.mini_project.productservice.productservice.repository.ProductRepository;
import com.mini_project.productservice.productservice.repository.ProductTempRepository;
import com.mini_project.productservice.productservice.service.interfaces.UpdateProductCatalogService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UpdateProductCatalogServiceImpl implements UpdateProductCatalogService {

    private ProductRepository productRepository;
    private ProductTempRepository productTempRepository;

    @Transactional
    @Override
    public ResponseEntity<?> submitChangeRequest(List<ProductCatalogAddRequest> requestList) {
        if (requestList == null || requestList.isEmpty()) {
            return ResponseEntity.badRequest().body("Request list cannot be null or empty.");
        }

        for (ProductCatalogAddRequest request : requestList) {
            try {
                ProductCatalogRequest productTemp = new ProductCatalogRequest();

                productTemp.setProductId(request.getProductId());
                productTemp.setSupplierId(request.getSupplierId());
                productTemp.setStatus(ProductStatus.PENDING);
                productTemp.setNewDescription(request.getNewDescription());
                productTemp.setNewImageUrl(request.getNewImageUrl());
                productTemp.setStock(request.getStock());
                productTemp.setPrice(request.getNewPrice());
                productTemp.setCreatedAt(LocalDateTime.now());

                productTempRepository.save(productTemp);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error processing request for product ID: " + request.getProductId() + ". " + e.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Change requests submitted successfully.");
    }

    @Transactional
    @Override
    public String generatePendingProductsCsv(String supplierId) {
        List<ProductCatalogRequest> pendingProducts = productTempRepository.findByStatusAndSupplierId(ProductStatus.PENDING, supplierId);
        String csvFilePath = "pending_products.csv";

        List<String[]> csvData = new ArrayList<>();
        String[] header = { "Request ID", "Product ID", "Supplier ID", "New Description", "New Image URL","New Stock","New Price", "Status", "Created At" };
        csvData.add(header);

        for (ProductCatalogRequest product : pendingProducts) {
            String[] row = new String[] {
                    String.valueOf(product.requestId),
                    product.getProductId(),
                    product.getSupplierId(),
                    product.getNewDescription(),
                    product.getNewImageUrl(),
                    String.valueOf(product.getStock()),
                    String.valueOf(product.getPrice()),
                    product.getStatus().name(),
                    product.getCreatedAt().toString()
            };
            csvData.add(row);
            System.out.println(product.toString());
        }

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(csvFilePath)))) {
            for (String[] csvRow : csvData) {
                writer.println(String.join(",", csvRow));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error generating CSV";
        }

        return csvFilePath;
    }

    @Transactional
    @Override
    public void saveApprovedProducts(List<Product> approvedProducts) {
        for (Product updatedProduct : approvedProducts) {
            Product currentProduct = productRepository.findByProductId(updatedProduct.getProductId());

            if (currentProduct != null) {
//                currentProduct.setImageUrl(updatedProduct.getImageUrl());
                currentProduct.setProductDescription(updatedProduct.getProductDescription());
                currentProduct.setStock(updatedProduct.getStock());
                currentProduct.setPrice(updatedProduct.getPrice());
                productRepository.save(currentProduct);
                productTempRepository.deleteByProductId(updatedProduct.getProductId());
            } else {
                System.out.println("Product not found: " + updatedProduct.getProductId());
            }
        }
    }


}

