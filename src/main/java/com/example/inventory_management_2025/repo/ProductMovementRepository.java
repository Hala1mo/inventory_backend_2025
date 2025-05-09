package com.example.inventory_management_2025.repo;

import com.example.inventory_management_2025.models.ProductMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMovementRepository extends JpaRepository<ProductMovement, Long> {
}