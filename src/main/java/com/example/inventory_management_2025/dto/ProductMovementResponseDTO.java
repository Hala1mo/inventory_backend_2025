package com.example.inventory_management_2025.dto;

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
    private String productName;
    private String fromLocationName;
    private String toLocationName;
    private int quantity;
    private MovementType movementType;
    private ShipmentStatus shipmentStatus;
    private String notes;
}
