package com.mini_project.productservice.productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "supplier_id", nullable = false)
    private String supplierId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "product_description")
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "category_name", nullable = false)
    private ProductCategory category;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}