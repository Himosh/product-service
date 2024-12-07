package com.mini_project.productservice.productservice.model;

import com.mini_project.productservice.productservice.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatalogRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long requestId;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String supplierId;

    private String newDescription;

    private String newImageUrl;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private int stock;

    private Double price;

    private LocalDateTime createdAt;

}
