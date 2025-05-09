package com.example.inventory_management_2025.services.impl;

import com.example.inventory_management_2025.dto.ProductMovementRequestDTO;
import com.example.inventory_management_2025.dto.ProductMovementResponseDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;
import com.example.inventory_management_2025.mapper.LocationMapper;
import com.example.inventory_management_2025.mapper.ProductMapper;
import com.example.inventory_management_2025.mapper.ProductMovementMapper;
import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.models.ProductMovement;
import com.example.inventory_management_2025.repo.LocationRepository;
import com.example.inventory_management_2025.repo.ProductMovementRepository;
import com.example.inventory_management_2025.repo.ProductRepository;
import com.example.inventory_management_2025.services.ProductMovementService;
import error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductMovementServiceImpl implements ProductMovementService {

    ProductMovementRepository productMovementRepository;
     ProductRepository productRepository;
     LocationRepository locationRepository;

    @Autowired
    public ProductMovementServiceImpl(
            ProductMovementRepository productMovementRepository,
            ProductRepository productRepository,
            LocationRepository locationRepository) {
        this.productMovementRepository = productMovementRepository;
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public ProductMovementResponseDTO createProductMovement(ProductMovementRequestDTO productMovementDTO) {
        Product product = productRepository.findById(productMovementDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productMovementDTO.getProductId() + " not found"));

        Location fromLocation = null;
        if (productMovementDTO.getFromLocationId() != null) {
            fromLocation = locationRepository.findById(productMovementDTO.getFromLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("From Location with ID " + productMovementDTO.getFromLocationId() + " not found"));
        }

        Location toLocation = null;
        if (productMovementDTO.getToLocationId() != null) {
            toLocation = locationRepository.findById(productMovementDTO.getToLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("To Location with ID " + productMovementDTO.getToLocationId() + " not found"));
        }

        ProductMovement movement = ProductMovementMapper.mapToEntity(productMovementDTO, product, fromLocation, toLocation);
        ProductMovement savedMovement = productMovementRepository.save(movement);

        return ProductMovementMapper.mapToDTO(savedMovement);
    }

    @Override
    public List<ProductMovementResponseDTO> getAllProductsMovement() {
        List<ProductMovement> queryResult = productMovementRepository.findAll();
        List<ProductMovementResponseDTO> productMovements = new ArrayList<>();
        for (ProductMovement productMovement : queryResult) {
            ProductMovementResponseDTO productDTO = ProductMovementMapper.mapToDTO(productMovement);
            productMovements.add(productDTO);
        }
        return productMovements;
    }

    @Override
    public ProductMovementResponseDTO getProductMovementById(long id) {
        ProductMovement product = productMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        return ProductMovementMapper.mapToDTO(product);
    }

    @Override
    public ProductMovementResponseDTO updateProductMovement(long id, ProductMovementRequestDTO dto) {
        // Check if movement exists
        ProductMovement existingMovement = productMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product movement with ID " + id + " not found"));

        // Get product and locations
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + dto.getProductId() + " not found"));

        Location fromLocation = null;
        if (dto.getFromLocationId() != null) {
            fromLocation = locationRepository.findById(dto.getFromLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("From Location with ID " + dto.getFromLocationId() + " not found"));
        }

        Location toLocation = null;
        if (dto.getToLocationId() != null) {
            toLocation = locationRepository.findById(dto.getToLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("To Location with ID " + dto.getToLocationId() + " not found"));
        }

        // Update fields
        existingMovement.setProduct(product);
        existingMovement.setFromLocation(fromLocation);
        existingMovement.setToLocation(toLocation);
        existingMovement.setMovementType(dto.getMovementType());
        existingMovement.setShipmentStatus(dto.getShipmentStatus());
        existingMovement.setQuantity(dto.getQuantity());
        existingMovement.setNotes(dto.getNotes());

        // Save and return
        ProductMovement updatedMovement = productMovementRepository.save(existingMovement);
        return ProductMovementMapper.mapToDTO(updatedMovement);
    }

    @Override
    public void deleteProductMovementById(long id) {

        ProductMovement location = productMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));
        if(location!=null) {
            productMovementRepository.deleteById(id);
        }

    }
}
