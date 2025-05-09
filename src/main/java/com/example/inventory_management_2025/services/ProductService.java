package com.example.inventory_management_2025.services;

import com.example.inventory_management_2025.dto.ProductRequestDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productDTO);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(long id);

    ProductResponseDTO updateProduct(long id,ProductRequestDTO productDTO);

    void deleteProductById(long id);
}
