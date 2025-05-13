package com.example.inventory_management_2025.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private int totalLocations;
    private int transferMovements;
    private int outMovements;
    private int inMovements;
    private int totalInventory;

}
