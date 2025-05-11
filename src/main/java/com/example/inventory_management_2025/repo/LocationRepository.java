package com.example.inventory_management_2025.repo;

import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {



    @Query(value = """
                SELECT
                    p.id AS product_id,
                    p.name AS product_name,
                    SUM(CASE WHEN pm.to_location_id = :locationId THEN pm.quantity ELSE 0 END) -
                    SUM(CASE WHEN pm.from_location_id = :locationId THEN pm.quantity ELSE 0 END) AS quantity
                FROM product_movement pm
                JOIN product p ON p.id = pm.product_id
                WHERE pm.to_location_id = :locationId OR pm.from_location_id = :locationId
                GROUP BY p.id, p.name
                HAVING quantity != 0
                ORDER BY p.name;
            """, nativeQuery = true)
    List<Object[]> fetchProductsInLocation(@Param("locationId") Long locationId);

}