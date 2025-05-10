package com.example.inventory_management_2025.dto;

import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.models.enums.MovementType;
import com.example.inventory_management_2025.models.enums.ShipmentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMovementResponseDTO {
    private Long id;
    private LocalDateTime timestamp;
    private Product product;
    private Location fromLocation;
    private Location toLocation;
    private int quantity;
    private MovementType movementType;
    private ShipmentStatus shipmentStatus;
    private String notes;
    private LocalDateTime createdAt;
}
