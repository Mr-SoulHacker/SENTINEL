package com.sentinel.sentinel.controller;

import com.sentinel.sentinel.model.VehicleProfileResponse;
import com.sentinel.sentinel.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private boolean isValidNumberPlate(String numberPlate) {
        return numberPlate != null
                && numberPlate.matches("[A-Z0-9]{10}");
    }

    @GetMapping("/{numberPlate}")
    public ResponseEntity<?> getVehicleStatus(
            @PathVariable String numberPlate) {

        if (!isValidNumberPlate(numberPlate)) {
            return ResponseEntity.badRequest()
                    .body("Invalid vehicle number format");
        }

        try {

            return ResponseEntity.ok(
                    vehicleService.buildIncidentResponse(numberPlate)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profile/{numberPlate}")
    public ResponseEntity<?> getVehicleProfile(
            @PathVariable String numberPlate) {

        if (!isValidNumberPlate(numberPlate)) {
            return ResponseEntity.badRequest()
                    .body("Invalid vehicle number format");
        }

        try {

            VehicleProfileResponse profile =
                    vehicleService.getVehicleProfile(numberPlate);

            return ResponseEntity.ok(profile);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body("Vehicle not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(
                vehicleService.getAllVehicles()
        );
    }
    @GetMapping("/history/{numberPlate}")
    public ResponseEntity<?> getHistory(
            @PathVariable String numberPlate) {

        if (!isValidNumberPlate(numberPlate)) {
            return ResponseEntity.badRequest()
                    .body("Invalid vehicle number format");
        }

        try {
            return ResponseEntity.ok(
                    vehicleService.getVehicleHistory(
                            numberPlate
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}