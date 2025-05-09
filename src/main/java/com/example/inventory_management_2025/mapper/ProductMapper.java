package com.example.inventory_management_2025.mapper;


import com.example.inventory_management_2025.dto.ProductRequestDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;
import com.example.inventory_management_2025.models.Product;

import java.time.LocalDateTime;

public class ProductMapper {


    public static ProductResponseDTO mapToDTO(Product product) {
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());
        return productDTO;
    }

    public static Product mapToEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setCategory(productRequestDTO.getCategory());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setImageUrl(productRequestDTO.getImageUrl());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }
}
