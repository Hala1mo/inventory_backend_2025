package com.example.inventory_management_2025.controllers;

import com.example.inventory_management_2025.dto.ProductMovementRequestDTO;
import com.example.inventory_management_2025.dto.ProductMovementResponseDTO;
import com.example.inventory_management_2025.dto.ProductRequestDTO;
import com.example.inventory_management_2025.dto.ProductResponseDTO;
import com.example.inventory_management_2025.services.ProductMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productMovement")
public class ProductMovementController {
    @Autowired
    private ProductMovementService productMovementService;

    @Operation(summary = "Create a new product movement")
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new product movement",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductMovementResponseDTO.class))}),
    })
    public ResponseEntity<ProductMovementResponseDTO> createProductMovement(@RequestBody ProductMovementRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productMovementService.createProductMovement(dto));
    }

    @Operation(summary = "Get all product movements")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all product movements",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Products not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    public ResponseEntity<List<ProductMovementResponseDTO>> getAllProductMovement() {
        return ResponseEntity.ok(productMovementService.getAllProductsMovement());
    }


    @Operation(summary = "Delete product movement by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid productMovementID", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductMovement(@PathVariable Long id) {
        productMovementService.deleteProductMovementById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update product movement by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated product movement",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))}),
            @ApiResponse(responseCode = "404s", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid productMovementID", content = @Content)
    })
    @PutMapping("/{id}")
    public ProductMovementResponseDTO updateProductMovement(@PathVariable Long id, @Valid @RequestBody ProductMovementRequestDTO updatedProduct) {
        return productMovementService.updateProductMovement(id, updatedProduct);
    }

    @Operation(summary = "Get product movement by id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the product movement",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductMovementResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "ProductMovement not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    public ResponseEntity<ProductMovementResponseDTO> getProductMovementByID(@PathVariable Long id) {
        return ResponseEntity.ok(productMovementService.getProductMovementById(id));
    }
}
