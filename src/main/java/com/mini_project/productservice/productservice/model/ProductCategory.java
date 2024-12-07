package com.mini_project.productservice.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "product_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    @Id
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date updatedDateTime;
}