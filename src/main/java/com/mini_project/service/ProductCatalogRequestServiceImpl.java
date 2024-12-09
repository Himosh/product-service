package com.mini_project.service;

import com.mini_project.model.Product;
import com.mini_project.model.ProductCategory;
import com.mini_project.model.ProductCatalogRequest;
import com.mini_project.model.dto.ProductCatalogRequestDTO;
import com.mini_project.model.dto.ProductDTO;
import com.mini_project.model.dto.UpdateProductStatusDTO;
import com.mini_project.model.enums.RequestStatus;
import com.mini_project.repository.ProductCategoryRepository;
import com.mini_project.repository.ProductRepository;
import com.mini_project.repository.ProductCatalogRequestRepository;
import com.mini_project.service.interfaces.ProductCatalogRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogRequestServiceImpl implements ProductCatalogRequestService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCatalogRequestRepository productCatalogRequestRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Transactional
//    public ResponseEntity<?> submitChangeRequest(List<ProductCatalogAddRequest> requestList) {
//        if (requestList == null || requestList.isEmpty()) {
//            return ResponseEntity.badRequest().body("Request list cannot be null or empty.");
//        }
//
//        for (ProductCatalogAddRequest request : requestList) {
//            try {
//                ProductCatalogRequest productTemp = new ProductCatalogRequest();
//
//                productTemp.setProductId(request.getProductId());
//                productTemp.setSupplierId(request.getSupplierId());
//                productTemp.setStatus(ProductStatus.PENDING);
//                productTemp.setNewDescription(request.getNewDescription());
//                productTemp.setNewImageUrl(request.getNewImageUrl());
//                productTemp.setStock(request.getStock());
//                productTemp.setPrice(request.getNewPrice());
//                productTemp.setCreatedAt(LocalDateTime.now());
//
//                productTempRepository.save(productTemp);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body("Error processing request for product ID: " + request.getProductId() + ". " + e.getMessage());
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("Change requests submitted successfully.");
//    }
//
//    @Transactional
//    public String generatePendingProductsCsv(String supplierId) {
//        List<ProductCatalogRequest> pendingProducts = productTempRepository.findByStatusAndSupplierId(ProductStatus.PENDING, supplierId);
//        String csvFilePath = "pending_products.csv";
//
//        List<String[]> csvData = new ArrayList<>();
//        String[] header = { "Request ID", "Product ID", "Supplier ID", "New Description", "New Image URL","New Stock","New Price", "Status", "Created At" };
//        csvData.add(header);
//
//        for (ProductCatalogRequest product : pendingProducts) {
//            String[] row = new String[] {
//                    String.valueOf(product.requestId),
//                    product.getProductId(),
//                    product.getSupplierId(),
//                    product.getNewDescription(),
//                    product.getNewImageUrl(),
//                    String.valueOf(product.getStock()),
//                    String.valueOf(product.getPrice()),
//                    product.getStatus().name(),
//                    product.getCreatedAt().toString()
//            };
//            csvData.add(row);
//            System.out.println(product.toString());
//        }
//
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(csvFilePath)))) {
//            for (String[] csvRow : csvData) {
//                writer.println(String.join(",", csvRow));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error generating CSV";
//        }
//
//        return csvFilePath;
//    }
//
//    @Transactional
//    public void saveApprovedProducts(List<Product> approvedProducts) {
//        for (Product updatedProduct : approvedProducts) {
//            Product currentProduct = productRepository.findByProductId(updatedProduct.getProductId());
//
//            if (currentProduct != null) {
////                currentProduct.setImageUrl(updatedProduct.getImageUrl());
//                currentProduct.setProductDescription(updatedProduct.getProductDescription());
//                currentProduct.setStock(updatedProduct.getStock());
//                currentProduct.setPrice(updatedProduct.getPrice());
//                productRepository.save(currentProduct);
//                productTempRepository.deleteByProductId(updatedProduct.getProductId());
//            } else {
//                System.out.println("Product not found: " + updatedProduct.getProductId());
//            }
//        }
//    }

    @Override
    public List<ProductCatalogRequest> createProductCatalogRequests(List<ProductCatalogRequestDTO> productCatalogRequests) {
        return productCatalogRequests.stream()
                .map(requestDTO -> {
                    ProductCatalogRequest productCatalogRequest = new ProductCatalogRequest();
                    productCatalogRequest.setSupplierId(requestDTO.getSupplierId());
                    productCatalogRequest.setRequestType(requestDTO.getRequestType());
                    productCatalogRequest.setStatus(RequestStatus.PENDING);

                    productCatalogRequest.setNewProductName(requestDTO.getNewProductName());
                    productCatalogRequest.setNewDescription(requestDTO.getNewDescription());
                    productCatalogRequest.setStock(requestDTO.getStock());
                    productCatalogRequest.setPrice(requestDTO.getPrice());

                    switch (requestDTO.getRequestType()) {
                        case ADD -> {
                            ProductCategory category = productCategoryRepository.findById(requestDTO.getCategoryId())
                                    .orElseThrow(() -> new RuntimeException("Category not found"));
                            productCatalogRequest.setCategory(category);
                        }
                        case UPDATE, DELETE -> {
                            Product product = productRepository.findById(requestDTO.getProductId())
                                    .orElseThrow(() -> new RuntimeException("Product not found for ID: " + requestDTO.getProductId()));
                            productCatalogRequest.setCategory(product.getCategory());
                            productCatalogRequest.setProductId(product.getId());
                        }
                        default -> throw new IllegalArgumentException("Unsupported request type");
                    }

                    return productCatalogRequestRepository.save(productCatalogRequest);
                })
                .toList();
    }


    @Override
    public List<ProductDTO> updateProductStatus(List<UpdateProductStatusDTO> updateProductStatusDTOS) {
        return updateProductStatusDTOS.stream()
                .map(updateProductStatusDTO -> {
                    ProductCatalogRequest productCatalogRequest = productCatalogRequestRepository.findById(updateProductStatusDTO.getProductCatalogRequestId())
                            .orElseThrow(() -> new RuntimeException("Product Catalog Request not found: " + updateProductStatusDTO.getProductCatalogRequestId()));

                    // Update request status
                    productCatalogRequest.setStatus(updateProductStatusDTO.getStatus());
                    productCatalogRequestRepository.save(productCatalogRequest);

                    if (updateProductStatusDTO.getStatus().equals(RequestStatus.APPROVED)) {
                        switch (productCatalogRequest.getRequestType()) {
                            case ADD -> handleAddProductRequest(productCatalogRequest);
                            case UPDATE -> handleUpdateProductRequest(productCatalogRequest);
                            case DELETE -> handleDeleteProductRequest(productCatalogRequest);
                            default -> throw new IllegalArgumentException("Unknown request type");
                        }
                    }

                    return modelMapper.map(productCatalogRequest, ProductDTO.class);
                })
                .toList();
    }
    private void handleAddProductRequest(ProductCatalogRequest productCatalogRequest) {
        Product product = new Product();
        product.setSupplierId(productCatalogRequest.getSupplierId());
        product.setName(productCatalogRequest.getNewProductName());
        product.setProductDescription(productCatalogRequest.getNewDescription());
        product.setStock(productCatalogRequest.getStock());
        product.setPrice(productCatalogRequest.getPrice());
        product.setCategory(productCatalogRequest.getCategory());
        productRepository.save(product);
    }

    private void handleUpdateProductRequest(ProductCatalogRequest productCatalogRequest) {
        Product product = productRepository.findById(productCatalogRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + productCatalogRequest.getProductId()));

        product.setName(productCatalogRequest.getNewProductName());
        product.setProductDescription(productCatalogRequest.getNewDescription());
        product.setStock(productCatalogRequest.getStock());
        product.setPrice(productCatalogRequest.getPrice());
        product.setCategory(productCatalogRequest.getCategory());
        productRepository.save(product);
    }
    private void handleDeleteProductRequest(ProductCatalogRequest productCatalogRequest) {
        Product product = productRepository.findById(productCatalogRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + productCatalogRequest.getProductId()));

        productRepository.delete(product);
    }

}

