package com.example.inventory_management_2025.services;

import com.example.inventory_management_2025.dto.ProductMovementRequestDTO;
import com.example.inventory_management_2025.dto.ProductMovementResponseDTO;
import com.example.inventory_management_2025.dto.ProductStockDTO;
import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;


import java.util.List;

public interface ProductMovementService {
    ProductMovementResponseDTO createProductMovement(ProductMovementRequestDTO productMovementDTO);

    List<ProductMovementResponseDTO> getAllProductsMovement();

    ProductMovementResponseDTO getProductMovementById(long id);

    ProductMovementResponseDTO updateProductMovement(long id,ProductMovementRequestDTO productMovementDTO);

    void deleteProductMovementById(long id);

    ProductStockDTO getSpecificProductsBalanceInLocation(Location location, Product product);
}
