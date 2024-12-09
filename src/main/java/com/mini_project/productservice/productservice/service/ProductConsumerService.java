package com.mini_project.productservice.productservice.service;

import com.mini_project.productservice.productservice.model.dto.ProductDTO;
import com.mini_project.productservice.productservice.model.dto.ProductRequest;
import com.mini_project.productservice.productservice.model.dto.ProductResponse;
import com.mini_project.productservice.productservice.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumerService {

    @Autowired
    private KafkaTemplate<String, ProductResponse> kafkaTemplate;

    @Autowired
    private ProductService productService;

    private static final String REQUEST_TOPIC = "product-request-topic";
    private static final String RESPONSE_TOPIC = "product-response-topic";

    @KafkaListener(topics = REQUEST_TOPIC, groupId = "product-service-group-5")
    public void consumeProductRequest(ProductRequest productRequest) {
        try {
            System.out.println("Received product request for productId: " + productRequest.getProductId());
            ProductDTO product = productService.getProductById(productRequest.getProductId());

            ProductResponse response = new ProductResponse();
            response.setRequestId(productRequest.getRequestId());
            response.setProductName(product.getName());
            response.setPrice(product.getPrice());
            response.setProductCategory(product.getCategoryName());
            response.setProductId(productRequest.getProductId());
            response.setSupplierId(product.getSupplierId());

            kafkaTemplate.send(RESPONSE_TOPIC, response);
        } catch (Exception e) {
            System.err.println("Error processing product request: " + e.getMessage());
        }
    }
}
