package com.example.inventory_management_2025.dto.reportsDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBalanceReportDTO {

    private Long locationId;
    private String locationName;
    private Long productId;
    private String productName;
    private Integer balance;

}