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

    Location findByName(String name);

    boolean existsByName(String name);

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


    @Query(value = """
                SELECT
                    l.id AS location_id,
                    l.name AS location_name,
                    COUNT(DISTINCT p.id) AS number_of_products
                FROM product_movement pm
                JOIN product p ON p.id = pm.product_id
                JOIN location l ON l.id = pm.to_location_id OR l.id = pm.from_location_id
                GROUP BY l.id, l.name
                HAVING SUM(CASE WHEN pm.to_location_id = l.id THEN pm.quantity ELSE 0 END) -
                       SUM(CASE WHEN pm.from_location_id = l.id THEN pm.quantity ELSE 0 END) > 0
            """, nativeQuery = true)
    List<Object[]> fetchProductCountsInAllLocations();


}