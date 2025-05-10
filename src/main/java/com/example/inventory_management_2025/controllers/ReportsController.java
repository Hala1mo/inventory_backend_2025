package com.example.inventory_management_2025.controllers;

import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;
import com.example.inventory_management_2025.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/product-balances")
    public List<ProductBalanceReportDTO> getProductsBalances() {
        return reportsService.getProductBalances();
    }

    @GetMapping("/product-balances/{productId}")
    public List<ProductBalanceReportDTO> getSpecificProductBalances(@PathVariable Long productId) {
        return reportsService.getSpecificProductBalances(productId);
    }
}
