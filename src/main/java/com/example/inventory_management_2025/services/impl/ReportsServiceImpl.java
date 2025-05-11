package com.example.inventory_management_2025.services.impl;

import com.example.inventory_management_2025.dto.reportsDTO.ProductBalanceReportDTO;
import com.example.inventory_management_2025.repo.ProductMovementRepository;
import com.example.inventory_management_2025.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportsServiceImpl implements ReportsService {
    ProductMovementRepository productMovementRepository;

    @Autowired
    public ReportsServiceImpl(ProductMovementRepository movementRepository) {

        this.productMovementRepository = movementRepository;
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
    public List<ProductBalanceReportDTO> getSpecificProductBalances(long product_id) {
        List<Object[]> resultsForSpecific = productMovementRepository.fetchProductBalanceByProductId(product_id);


        return resultsForSpecific.stream()
                .map(row -> new ProductBalanceReportDTO(
                        ((Number) row[0]).longValue(),   // location_id
                        (String) row[1],                 // location_name
                        ((Number) row[2]).longValue(),   // product_id
                        (String) row[3],                 // product_name
                        ((Number) row[4]).intValue()     // balance
                ))
                .toList();
    }


}
