package com.example.inventory_management_2025.mapper;

import com.example.inventory_management_2025.dto.ProductMovementRequestDTO;
import com.example.inventory_management_2025.dto.ProductMovementResponseDTO;
import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.models.ProductMovement;

import java.time.LocalDateTime;

public class ProductMovementMapper {

    public static ProductMovementResponseDTO mapToDTO(ProductMovement movement) {
        ProductMovementResponseDTO dto = new ProductMovementResponseDTO();
        dto.setId(movement.getId());
        dto.setMovementType(movement.getMovementType());
        dto.setProductName(movement.getProduct() != null ? movement.getProduct().getName() : null);
        dto.setFromLocationName(movement.getFromLocation() != null ? movement.getFromLocation().getName() : null);
        dto.setToLocationName(movement.getToLocation() != null ? movement.getToLocation().getName() : null);
        dto.setShipmentStatus(movement.getShipmentStatus());
        dto.setQuantity(movement.getQuantity());
        dto.setNotes(movement.getNotes());
        dto.setTimestamp(movement.getTimestamp());
        return dto;
    }

    public static ProductMovement mapToEntity(ProductMovementRequestDTO dto,
                                              Product product,
                                              Location fromLocation,
                                              Location toLocation) {
        ProductMovement movement = new ProductMovement();
        movement.setProduct(product);
        movement.setFromLocation(fromLocation);
        movement.setToLocation(toLocation);
        movement.setMovementType(dto.getMovementType());
        movement.setShipmentStatus(dto.getShipmentStatus());
        movement.setQuantity(dto.getQuantity());
        movement.setNotes(dto.getNotes());
        movement.setTimestamp(LocalDateTime.now());
        return movement;
    }
}
