package com.example.inventory_management_2025.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationStockDTO {
    private String locationName;
    private int quantity;
}
