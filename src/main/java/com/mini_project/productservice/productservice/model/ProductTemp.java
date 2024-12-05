package com.mini_project.productservice.productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemp {
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
    private Status status;

    private int stock;

    private BigDecimal price;

    private LocalDateTime createdAt;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }
}
