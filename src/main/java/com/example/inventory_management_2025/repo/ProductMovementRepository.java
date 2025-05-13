package com.example.inventory_management_2025.repo;

import com.example.inventory_management_2025.models.Location;
import com.example.inventory_management_2025.models.Product;
import com.example.inventory_management_2025.models.ProductMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMovementRepository extends JpaRepository<ProductMovement, Long> {

    @Query(value = """
            SELECT
                location_id,
                location_name,
                product_id,
                product_name,
                SUM(in_qty) - SUM(out_qty) AS balance
            FROM (
                SELECT
                    l.id AS location_id,
                    l.name AS location_name,
                    p.id AS product_id,
                    p.name AS product_name,
                    CASE WHEN pm.to_location_id = l.id THEN pm.quantity ELSE 0 END AS in_qty,
                    CASE WHEN pm.from_location_id = l.id THEN pm.quantity ELSE 0 END AS out_qty
                FROM product_movement pm
                JOIN product p ON p.id = pm.product_id
                JOIN location l ON
                    l.id = pm.to_location_id OR l.id = pm.from_location_id
            ) AS movements
            GROUP BY location_id, location_name, product_id, product_name
            HAVING SUM(in_qty) - SUM(out_qty) != 0
            ORDER BY location_name, product_name;
                        """, nativeQuery = true)
    List<Object[]> fetchProductBalances();


    @Query(value = """
            SELECT
                p.id AS product_id,
                p.name AS product_name,
                SUM(CASE WHEN pm.to_location_id = :locationId THEN pm.quantity ELSE 0 END) -
                SUM(CASE WHEN pm.from_location_id = :locationId THEN pm.quantity ELSE 0 END) AS quantity
            FROM product_movement pm
            JOIN product p ON p.id = pm.product_id
            WHERE (pm.to_location_id = :locationId OR pm.from_location_id = :locationId)
              AND pm.product_id = :productId
            GROUP BY p.id, p.name
            HAVING quantity != 0
            ORDER BY p.name
            """, nativeQuery = true)
    Object fetchProductBalanceInLocation(@Param("locationId") Long locationId, @Param("productId") Long productId);

    boolean existsByProduct(Product product);

    boolean existsByFromLocation(Location location);

    boolean existsByToLocation(Location location);


    @Query(value = """
    SELECT 
        (SELECT COUNT(*) FROM location) AS totalLocations,
        (SELECT COUNT(*) FROM product_movement WHERE movement_type = 'TRANSFER') AS transferMovements,
        (SELECT COUNT(*) FROM product_movement WHERE movement_type = 'OUT') AS outMovements,
        (SELECT COUNT(*) FROM product_movement WHERE movement_type = 'IN') AS inMovements,
        (SELECT SUM(CASE 
                    WHEN to_location_id IS NOT NULL THEN quantity 
                    ELSE 0 END) - 
                SUM(CASE 
                    WHEN from_location_id IS NOT NULL THEN quantity 
                    ELSE 0 END)
         FROM product_movement) AS totalProductInventory
    """, nativeQuery = true)
    Object fetchDashboardStats();


}
