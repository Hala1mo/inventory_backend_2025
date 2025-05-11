package com.example.inventory_management_2025.dto;

import lombok.*;

import java.time.LocalDateTime;

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
    private String address;
    private LocalDateTime createdAt;
}
