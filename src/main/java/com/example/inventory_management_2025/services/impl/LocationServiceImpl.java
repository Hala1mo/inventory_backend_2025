package com.example.inventory_management_2025.services.impl;

import com.example.inventory_management_2025.dto.LocationRequestDTO;
import com.example.inventory_management_2025.dto.LocationResponseDTO;
import com.example.inventory_management_2025.mapper.LocationMapper;
import com.example.inventory_management_2025.mapper.ProductMapper;
import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.repo.LocationRepository;
import com.example.inventory_management_2025.services.LocationService;

import error.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public LocationResponseDTO createLocation(LocationRequestDTO locationDTO) {
        Location location = LocationMapper.mapToEntity(locationDTO);
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
        Location existingProduct = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));

        existingProduct.setName(locationDTO.getName());
        existingProduct.setCountry(locationDTO.getCountry());
        existingProduct.setCity(locationDTO.getCity());
        existingProduct.setLatitude(locationDTO.getLatitude());
        existingProduct.setLongitude(locationDTO.getLongitude());
        Location updatedProduct = locationRepository.save(existingProduct);

        return LocationMapper.mapToDTO(updatedProduct);
    }

    @Override
    public void deleteLocationById(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found"));
        if(location!=null) {
            locationRepository.deleteById(id);
        }
    }
}
