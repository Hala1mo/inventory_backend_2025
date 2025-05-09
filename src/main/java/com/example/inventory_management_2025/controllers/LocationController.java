package com.example.inventory_management_2025.controllers;

import com.example.inventory_management_2025.dto.*;
import com.example.inventory_management_2025.dto.LocationRequestDTO;
import com.example.inventory_management_2025.dto.LocationResponseDTO;
import com.example.inventory_management_2025.services.LocationService;
import com.example.inventory_management_2025.services.LocationService;
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
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Create a new location")
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new location",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LocationResponseDTO.class))}),
    })
    public ResponseEntity<LocationResponseDTO> createLocation(@RequestBody LocationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.createLocation(dto));
    }

    @Operation(summary = "Get all locations")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all locations",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LocationResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Locations not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    public ResponseEntity<List<LocationResponseDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }


    @Operation(summary = "Delete location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Location successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Location not found", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Update location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated an product",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LocationResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Location not found", content = @Content),
    })
    @PutMapping("/{id}")
    public LocationResponseDTO updateLocation(@PathVariable Long id, @Valid @RequestBody LocationRequestDTO updatedLocation) {
        return locationService.updateLocation(id, updatedLocation);
    }



    @Operation(summary = "Get location by id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the location",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LocationResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Location not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    public ResponseEntity<LocationResponseDTO> getLocationByID(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }
}
