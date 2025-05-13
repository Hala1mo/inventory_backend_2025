package com.example.inventory_management_2025.dto;

import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.models.enums.MovementType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMovementResponseDTO {
    private Long id;
    private Product product;
    private Location fromLocation;
    private Location toLocation;
    private int quantity;
    private MovementType movementType;
    private String notes;
    private LocalDateTime createdAt;
}
