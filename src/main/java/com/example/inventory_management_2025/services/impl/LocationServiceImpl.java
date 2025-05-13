package com.example.inventory_management_2025.services.impl;

import com.example.inventory_management_2025.dto.LocationRequestDTO;
import com.example.inventory_management_2025.dto.LocationResponseDTO;
import com.example.inventory_management_2025.dto.ProductStockDTO;
import com.example.inventory_management_2025.mapper.LocationMapper;
import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.repo.LocationRepository;
import com.example.inventory_management_2025.repo.ProductMovementRepository;
import com.example.inventory_management_2025.services.LocationService;

import error.CustomBadRequestException;
import error.DeletionNotAllowedException;
import error.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    LocationRepository locationRepository;
    ProductMovementRepository productMovementRepository;
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ProductMovementRepository productMovementRepository) {
        this.locationRepository = locationRepository;
        this.productMovementRepository=productMovementRepository;
    }

    @Override
    public LocationResponseDTO createLocation(LocationRequestDTO locationDTO) {
        Location location = LocationMapper.mapToEntity(locationDTO);
        if (locationRepository.existsByName(location.getName())) {
            throw new CustomBadRequestException("Location with name '" + location.getName() + "' already exists.");
        }
        Location newLocation = locationRepository.save(location);
        return LocationMapper.mapToDTO(newLocation);
    }

    @Override
    public List<LocationResponseDTO> getAllLocations() {
        List<Location> queryResult = locationRepository.findAll();
        List<LocationResponseDTO> locations = new ArrayList<>();
        for (Location location : queryResult) {
            LocationResponseDTO productDTO = LocationMapper.mapToDTO(location);
            locations.add(productDTO);
        }
        return locations;
    }

    @Override
    public LocationResponseDTO getLocationById(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));
        return LocationMapper.mapToDTO(location);
    }

    @Override
    public LocationResponseDTO updateLocation(long id, LocationRequestDTO locationDTO) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));

        existingLocation.setName(locationDTO.getName());
        existingLocation.setCountry(locationDTO.getCountry());
        existingLocation.setCity(locationDTO.getCity());
        existingLocation.setAddress(locationDTO.getAddress());
        Location updatedProduct = locationRepository.save(existingLocation);

        return LocationMapper.mapToDTO(updatedProduct);
    }

    @Override
    public void deleteLocationById(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));

        if (productMovementRepository.existsByToLocation(location) || productMovementRepository.existsByFromLocation(location)) {
            throw new DeletionNotAllowedException("This location has recorded inventory movements and cannot be deleted.");
        }
        if (location != null) {
            locationRepository.deleteById(id);
        }
    }

    @Override
    public List<ProductStockDTO> getProductsInLocation(long id) {
        List<Object[]> row = locationRepository.fetchProductsInLocation(id);
        return row.stream()
                .map(r -> new ProductStockDTO(
                        ((Number) r[0]).longValue(),
                        (String) r[1],
                        ((Number) r[2]).intValue()
                ))
                .toList();
    }


}
