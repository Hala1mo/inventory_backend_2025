package com.example.inventory_management_2025.repo;

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
                      WHERE pm.product_id = :productId
            ) AS movements
            GROUP BY location_id, location_name, product_id, product_name
            HAVING SUM(in_qty) - SUM(out_qty) != 0
            ORDER BY location_name, product_name;
                        """, nativeQuery = true)
    List<Object[]> fetchProductBalanceByProductId(@Param("productId") Long productId);
}
