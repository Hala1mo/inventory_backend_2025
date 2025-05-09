package com.example.inventory_management_2025.repo;

import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}