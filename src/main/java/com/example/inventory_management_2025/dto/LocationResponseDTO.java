package com.example.inventory_management_2025.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private Double longitude;
    private Double latitude;
}
