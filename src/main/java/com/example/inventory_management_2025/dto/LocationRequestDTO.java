package com.example.inventory_management_2025.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequestDTO {
    private String name;
    private String country;
    private String city;
    private String address;
}
