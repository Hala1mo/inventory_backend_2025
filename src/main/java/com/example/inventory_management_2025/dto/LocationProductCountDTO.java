package com.example.inventory_management_2025.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationProductCountDTO {
    private Long locationId;
    private String locationName;
    private Integer quantity;
}


