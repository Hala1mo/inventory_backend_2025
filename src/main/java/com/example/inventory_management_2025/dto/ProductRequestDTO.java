package com.example.inventory_management_2025.dto;

import com.example.inventory_management_2025.models.enums.ProductCategory;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;
    private Double price;
    private String description;
    private ProductCategory category;
    private String imageUrl;
}
