package com.mini_project.productservice.productservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductCatalogChangeRequest {
    private String productId;

    private String supplierId;

    private String newDescription;

    private String newImageUrl;

    private int stock;

    private BigDecimal newPrice;
}

