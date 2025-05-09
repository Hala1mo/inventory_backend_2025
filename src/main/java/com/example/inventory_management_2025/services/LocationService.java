package com.example.inventory_management_2025.services;



import com.example.inventory_management_2025.dto.LocationRequestDTO;
import com.example.inventory_management_2025.dto.LocationResponseDTO;

import java.util.List;

public interface LocationService {
    LocationResponseDTO createLocation(LocationRequestDTO locationDTO);

    List<LocationResponseDTO> getAllLocations();

    LocationResponseDTO getLocationById(long id);

    LocationResponseDTO updateLocation(long id, LocationRequestDTO locationDTO);

    void deleteLocationById(long id);
}
