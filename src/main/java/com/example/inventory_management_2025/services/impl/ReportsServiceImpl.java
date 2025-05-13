package com.example.inventory_management_2025.services.impl;

import com.example.inventory_management_2025.dto.DashboardStatsDTO;
import com.example.inventory_management_2025.dto.LocationProductCountDTO;
import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;
import com.example.inventory_management_2025.repo.LocationRepository;
import com.example.inventory_management_2025.repo.ProductMovementRepository;
import com.example.inventory_management_2025.repo.ProductRepository;
import com.example.inventory_management_2025.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportsServiceImpl implements ReportsService {
    ProductMovementRepository productMovementRepository;

    ProductRepository productRepository;

    LocationRepository locationRepository;
    @Autowired
    public ReportsServiceImpl(ProductMovementRepository movementRepository, ProductRepository productRepository , LocationRepository locationRepository ) {

        this.productMovementRepository = movementRepository;
        this.productRepository= productRepository;
        this.locationRepository= locationRepository;
    }

    public List <ProductBalanceReportDTO> getProductBalances() {
        List<Object[]> results = productMovementRepository.fetchProductBalances();

        return results.stream()
                .map(row -> new ProductBalanceReportDTO(
                        ((Number) row[0]).longValue(),   // location_id
                        (String) row[1],                 // location_name
                        ((Number) row[2]).longValue(),   // product_id
                        (String) row[3],                 // product_name
                        ((Number) row[4]).intValue()     // balance
                ))
                .toList();
    }

    @Override
    public Map<String, Integer> getProductCategoryDistribution() {
        List<Object[]> rawData = productRepository.getProductCategoryDistribution();
        Map<String, Integer> distribution = new HashMap<>();

        for (Object[] row : rawData) {
            String category = (String) row[0];
            Integer count = ((Number) row[1]).intValue();
            distribution.put(category, count);
        }

        return distribution;
    }

    public List<LocationProductCountDTO> fetchProductCountsInAllLocations() {
        List<Object[]> raw = locationRepository.fetchProductCountsInAllLocations();
        return raw.stream().map(r -> new LocationProductCountDTO(
                ((Number) r[0]).longValue(),
                (String) r[1],
                ((Number) r[2]).intValue()
        )).toList();
    }


    public DashboardStatsDTO fetchDashboardStats() {
        Object result = productMovementRepository.fetchDashboardStats();

        Object[] row = (Object[]) result;
        return new DashboardStatsDTO(
                ((Number) row[0]).intValue(),  // totalLocations
                ((Number) row[1]).intValue(),  // transferMovements
                ((Number) row[2]).intValue(),  // outMovements
                ((Number) row[3]).intValue(),  // inMovements
                ((Number) row[4]).intValue()   // totalProductInventory
        );
    }


}
