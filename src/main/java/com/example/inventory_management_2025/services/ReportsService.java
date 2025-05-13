package com.example.inventory_management_2025.services;
import com.example.inventory_management_2025.dto.DashboardStatsDTO;
import com.example.inventory_management_2025.dto.LocationProductCountDTO;
import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;

import java.util.List;
import java.util.Map;

public interface ReportsService {
    List<ProductBalanceReportDTO> getProductBalances();

     Map<String, Integer> getProductCategoryDistribution();

    List<LocationProductCountDTO> fetchProductCountsInAllLocations();

     DashboardStatsDTO fetchDashboardStats();
}
