package com.mini_project.productservice.productservice.model;

import com.mini_project.productservice.productservice.model.enums.RequestStatus;
import com.mini_project.productservice.productservice.model.enums.RequestType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatalogRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Long productId;

    @Column(nullable = false)
    private String supplierId;

    private String newProductName;

    private String newDescription;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    private int stock;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_name", nullable = false)
    private ProductCategory category;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date updatedDateTime;

}
