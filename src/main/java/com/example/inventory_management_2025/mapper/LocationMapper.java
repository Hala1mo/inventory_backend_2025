package com.example.inventory_management_2025.mapper;

import com.example.inventory_management_2025.dto.LocationRequestDTO;
import com.example.inventory_management_2025.dto.LocationResponseDTO;
import com.example.inventory_management_2025.models.Location;
import java.time.LocalDateTime;

public class LocationMapper {

    public static LocationResponseDTO mapToDTO(Location location) {
        LocationResponseDTO locationDTO = new LocationResponseDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setCity(location.getCity());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        return locationDTO;
    }

    public static Location mapToEntity(LocationRequestDTO locationRequestDTO) {
        Location location = new Location();
        location.setName(locationRequestDTO.getName());
        location.setCity(locationRequestDTO.getCity());
        location.setCountry(locationRequestDTO.getCountry());
        location.setLatitude(locationRequestDTO.getLatitude());
        location.setLongitude(locationRequestDTO.getLongitude());
        location.setCreatedAt(LocalDateTime.now());
        return location;
    }
}
