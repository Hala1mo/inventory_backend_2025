package com.example.inventory_management_2025.services.impl;


import com.example.inventory_management_2025.dto.ProductRequestDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;
import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.repo.ProductMovementRepository;
import com.example.inventory_management_2025.repo.ProductRepository;
import com.example.inventory_management_2025.services.ProductService;
import error.CustomBadRequestException;
import error.DeletionNotAllowedException;
import error.ResourceNotFoundException;
import com.example.inventory_management_2025.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMovementRepository productMovementRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,ProductMovementRepository productMovementRepository ) {
        this.productRepository = productRepository;
        this.productMovementRepository = productMovementRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.mapToEntity(productRequestDTO);
        if (productRepository.existsByCode(productRequestDTO.getCode())) {
            throw new CustomBadRequestException("Product with code '" + productRequestDTO.getCode() + "' already exists.");
        }

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
        existingProduct.setCode(productDTO.getCode());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setImageUrl(productDTO.getImageUrl());
        existingProduct.setStatus(productDTO.getStatus());
        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));


        if (productMovementRepository.existsByProduct(product)) {
            throw new DeletionNotAllowedException("This product has recorded inventory movements and cannot be deleted.");
        }

        if (product != null) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public List<ProductBalanceReportDTO> getSpecificProductBalances(long product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + product_id + " not found"));
        List<Object[]> resultsForSpecific = productRepository.fetchProductBalanceByProductId(product_id);


        return resultsForSpecific.stream()
                .map(row -> new ProductBalanceReportDTO(
                        ((Number) row[0]).longValue(),   // location_id
                        (String) row[1],                 // location_name
                        ((Number) row[2]).longValue(),   // product_id
                        (String) row[3],                 // product_name
                        ((Number) row[4]).intValue()     // balance
                ))
                .toList();
    }
}
