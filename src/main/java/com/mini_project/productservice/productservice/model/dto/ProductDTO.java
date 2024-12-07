package com.mini_project.productservice.productservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private Long categoryId;
    private String supplierId;
    private String name;
    private String productDescription;
    private String categoryName;
    private int stock;
    private BigDecimal price;
}