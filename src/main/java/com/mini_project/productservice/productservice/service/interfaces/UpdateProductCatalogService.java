package com.mini_project.productservice.productservice.service.interfaces;

import com.mini_project.productservice.productservice.model.dto.ProductCatalogAddRequest;
import com.mini_project.productservice.productservice.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UpdateProductCatalogService {

    /**
     * Submits a change request for a list of product catalog changes.
     *
     * @param requestList List of product catalog change requests.
     * @return ResponseEntity indicating the success or failure of the request.
     */
    ResponseEntity<?> submitChangeRequest(List<ProductCatalogAddRequest> requestList);

    /**
     * Generates a CSV file containing pending product change requests.
     *
     * @param supplierId The ID of the supplier whose pending products are to be fetched.
     * @return The path of the generated CSV file.
     */
    String generatePendingProductsCsv(String supplierId);

    /**
     * Saves the approved products after a change request has been processed.
     *
     * @param approvedProducts List of approved products to save.
     */
    void saveApprovedProducts(List<Product> approvedProducts);
}