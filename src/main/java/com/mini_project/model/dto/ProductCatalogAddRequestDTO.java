package com.mini_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductCatalogAddRequestDTO {
    private String supplierId;
    private Long categoryId;
    private String name;
    private String description;
    private int stock;
    private Double price;
}

