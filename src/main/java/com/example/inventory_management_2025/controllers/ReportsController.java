package com.example.inventory_management_2025.controllers;

import com.example.inventory_management_2025.dto.DashboardStatsDTO;
import com.example.inventory_management_2025.dto.LocationProductCountDTO;
import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;
import com.example.inventory_management_2025.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/product-balances")
    public List<ProductBalanceReportDTO> getProductsBalances() {
        return reportsService.getProductBalances();
    }

    @GetMapping("/productDistribution")
    public ResponseEntity<Map<String, Integer>> getProductCategoryDistribution() {
        return ResponseEntity.ok(reportsService.getProductCategoryDistribution());
    }

    @GetMapping("/product-counts-per-location")
    public List<LocationProductCountDTO> getProductCountInAllLocation() {
        return reportsService.fetchProductCountsInAllLocations();
    }


    @GetMapping("/dashboard-stats")
    public DashboardStatsDTO getDashboardStats() {
        return reportsService.fetchDashboardStats();
    }


}
