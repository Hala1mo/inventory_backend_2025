package com.example.inventory_management_2025.services.impl;


import com.example.inventory_management_2025.dto.ProductRequestDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.repo.ProductRepository;
import com.example.inventory_management_2025.services.ProductService;
import error.ResourceNotFoundException;
import com.example.inventory_management_2025.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.mapToEntity(productRequestDTO);
        Product newProduct = productRepository.save(product);
        return ProductMapper.mapToDTO(newProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> queryResult = productRepository.findAll();
        List<ProductResponseDTO> products = new ArrayList<>();
        for (Product product : queryResult) {
            ProductResponseDTO productDTO = ProductMapper.mapToDTO(product);
            products.add(productDTO);
        }
        return products;
    }

    @Override
    public ProductResponseDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        return ProductMapper.mapToDTO(product);
    }


    @Override
    public ProductResponseDTO updateProduct(long id, ProductRequestDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setImageUrl(productDTO.getImageUrl());
        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        if (product != null) {
            productRepository.deleteById(id);
        }
    }
}
