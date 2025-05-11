package com.example.inventory_management_2025.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDTO {

    private Long productId;
    private String productName;
    private Integer quantity;

}