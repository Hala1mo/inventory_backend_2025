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
        dto.setProduct(movement.getProduct());
        dto.setFromLocation(movement.getFromLocation());
        dto.setToLocation(movement.getToLocation());
        dto.setQuantity(movement.getQuantity());
        dto.setNotes(movement.getNotes());
        dto.setCreatedAt(movement.getCreatedAt());
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
        movement.setQuantity(dto.getQuantity());
        movement.setNotes(dto.getNotes());
        movement.setCreatedAt(LocalDateTime.now());
        return movement;
    }
}
