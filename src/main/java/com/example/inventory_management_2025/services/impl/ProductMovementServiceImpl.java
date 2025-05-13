package com.example.inventory_management_2025.services.impl;

import com.example.inventory_management_2025.dto.ProductMovementRequestDTO;
import com.example.inventory_management_2025.dto.ProductMovementResponseDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;
import com.example.inventory_management_2025.dto.ProductStockDTO;
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
import error.CustomBadRequestException;
import error.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
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
        Product product = productRepository.findByCode(productMovementDTO.getProduct().getCode());
        if(product==null)
                throw new ResourceNotFoundException("Product with Name " + productMovementDTO.getProduct().getName() + " not found");



        //INCOMING
        Location fromLocation = null;
        if (productMovementDTO.getFromLocation()!= null) {
            fromLocation = locationRepository.findByName(productMovementDTO.getFromLocation().getName());
            if(fromLocation==null)
                throw new ResourceNotFoundException("Source Location with Name " + productMovementDTO.getFromLocation().getName() + " not found");


            ProductStockDTO currentStock = getSpecificProductsBalanceInLocation(fromLocation, product);
            if (currentStock.getQuantity() < productMovementDTO.getQuantity()) {
                throw new CustomBadRequestException("Not enough stock at source location");
            }

        }
  ///OUTGOING
        Location toLocation = null;
        if (productMovementDTO.getToLocation() != null) {
            toLocation = locationRepository.findByName(productMovementDTO.getToLocation().getName());
            if(toLocation==null)
                throw new ResourceNotFoundException("Destination Location with Name " + productMovementDTO.getToLocation().getName() + " not found");
        }

        if (productMovementDTO.getFromLocation() != null  && productMovementDTO.getToLocation()!= null  && productMovementDTO.getFromLocation().getName().equals(productMovementDTO.getToLocation().getName())) {
            throw new CustomBadRequestException("Cannot move product within the same location");
        }
        if (productMovementDTO.getQuantity() <= 0) {
            throw new CustomBadRequestException("Quantity must be greater than zero");
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
        ProductMovement existingMovement = productMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product movement with ID " + id + " not found"));

        existingMovement.setProduct(existingMovement.getProduct());
        existingMovement.setFromLocation(existingMovement.getFromLocation());
        existingMovement.setToLocation(existingMovement.getToLocation());
        existingMovement.setMovementType(dto.getMovementType());
        existingMovement.setQuantity(dto.getQuantity());
        existingMovement.setNotes(dto.getNotes());


        ProductMovement updatedMovement = productMovementRepository.save(existingMovement);
        return ProductMovementMapper.mapToDTO(updatedMovement);
    }

    @Override
    public void deleteProductMovementById(long id) {

        ProductMovement location = productMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));
        if (location != null) {
            productMovementRepository.deleteById(id);
        }

    }

    @Override
    public ProductStockDTO getSpecificProductsBalanceInLocation(Location location, Product product) {
        Object result = productMovementRepository.fetchProductBalanceInLocation(location.getId(), product.getId());

        if (result == null) {
            throw new CustomBadRequestException("Product with Name " + product.getName() + " not found in location " + location.getName());
        }

        Object[] row = (Object[]) result;

        return new ProductStockDTO(
                ((Number) row[0]).longValue(),  // product_id
                (String) row[1],                // product_name
                ((Number) row[2]).intValue()    // quantity
        );
    }

}
