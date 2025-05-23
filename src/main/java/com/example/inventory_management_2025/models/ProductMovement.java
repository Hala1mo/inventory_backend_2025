package com.example.inventory_management_2025.models;

import com.example.inventory_management_2025.models.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "from_location_id")
    private Location fromLocation;

    @ManyToOne
    @JoinColumn(name = "to_location_id")
    private Location toLocation;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType movementType;


    private String notes;

    @Column(nullable = false)
    private LocalDateTime createdAt;



}


