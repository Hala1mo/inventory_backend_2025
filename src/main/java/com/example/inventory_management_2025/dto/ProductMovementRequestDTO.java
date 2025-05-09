package com.example.inventory_management_2025.dto;

import com.example.inventory_management_2025.models.enums.MovementType;
import com.example.inventory_management_2025.models.enums.ShipmentStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMovementRequestDTO {
    private Long productId;
    private Long fromLocationId;  // nullable
    private Long toLocationId;    // nullable
    private int quantity;
    private MovementType movementType;
    private ShipmentStatus shipmentStatus;
    private String notes;
}
