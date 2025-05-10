package com.example.inventory_management_2025.models;

import com.example.inventory_management_2025.models.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import com.example.inventory_management_2025.models.enums.ProductCategory;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Double price;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProductCategory category;


    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

}
