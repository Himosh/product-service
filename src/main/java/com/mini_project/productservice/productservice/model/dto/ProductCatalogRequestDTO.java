package com.mini_project.productservice.productservice.model.dto;

import com.mini_project.productservice.productservice.model.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductCatalogRequestDTO {
    private Long productId;
    private Long categoryId;
    private String supplierId;
    private String newProductName;
    private String newDescription;
    private int stock;
    private Double price;
    private RequestType requestType;
}

