package com.example.inventory_management_2025.dto;

import com.example.inventory_management_2025.models.enums.ProductCategory;
import com.example.inventory_management_2025.models.enums.ProductStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponseDTO {
    private Long id;
    private String name;
    private String code;
    private Double price;
    private String description;
    private ProductCategory category;
    private String imageUrl;
    private ProductStatus status;
    private LocalDateTime createdAt;

}
