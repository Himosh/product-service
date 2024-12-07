package com.mini_project.productservice.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductCatalogAddRequest {
    private String productId;

    private String supplierId;

    private String newDescription;

    private String newImageUrl;

    private int stock;

    private Double newPrice;
}

