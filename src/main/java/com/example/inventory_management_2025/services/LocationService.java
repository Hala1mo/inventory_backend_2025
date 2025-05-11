package com.example.inventory_management_2025.services;



import com.example.inventory_management_2025.dto.LocationRequestDTO;
import com.example.inventory_management_2025.dto.LocationResponseDTO;
import com.example.inventory_management_2025.dto.ProductStockDTO;
import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;

import java.util.List;

public interface LocationService {
    LocationResponseDTO createLocation(LocationRequestDTO locationDTO);

    List<LocationResponseDTO> getAllLocations();

    LocationResponseDTO getLocationById(long id);

    LocationResponseDTO updateLocation(long id, LocationRequestDTO locationDTO);

    void deleteLocationById(long id);

    List<ProductStockDTO> getProductsInLocation(long id);
}
