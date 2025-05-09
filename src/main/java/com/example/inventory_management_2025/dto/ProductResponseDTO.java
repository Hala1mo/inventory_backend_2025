package com.example.inventory_management_2025.dto;

import com.example.inventory_management_2025.models.enums.ProductCategory;
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
    private Double price;
    private String description;
    private ProductCategory category;
    private String imageUrl;

}
