package com.example.inventory_management_2025.services;
import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;

import java.util.List;

public interface ReportsService {
    List<ProductBalanceReportDTO> getProductBalances();
    List<ProductBalanceReportDTO> getSpecificProductBalances(long productID);


}
