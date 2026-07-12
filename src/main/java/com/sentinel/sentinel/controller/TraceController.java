package com.sentinel.sentinel.controller;

import com.sentinel.sentinel.model.IncidentReport;
import com.sentinel.sentinel.model.IncidentResponse;
import com.sentinel.sentinel.model.IncidentType;
import com.sentinel.sentinel.service.AlertLogService;
import com.sentinel.sentinel.service.IncidentReportService;
import com.sentinel.sentinel.service.IncidentService;
import com.sentinel.sentinel.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trace")
public class TraceController {

    private final VehicleService vehicleService;
    private final IncidentService incidentService;
    private final AlertLogService alertLogService;
    private final IncidentReportService incidentReportService;

    public TraceController(
            VehicleService vehicleService,
            IncidentService incidentService,
            AlertLogService alertLogService,
            IncidentReportService incidentReportService
    ) {

        this.vehicleService = vehicleService;
        this.incidentService = incidentService;
        this.alertLogService = alertLogService;
        this.incidentReportService = incidentReportService;
    }

    @GetMapping("/{numberPlate}")
    public ResponseEntity<?> traceVehicle(
            @PathVariable String numberPlate
    ) {

        try {

            IncidentResponse response = null;

            try {

                response =
                        vehicleService
                                .buildIncidentResponse(numberPlate);

                IncidentType type =
                        IncidentType.valueOf(
                                response.getIncidentType()
                        );

                incidentService.logIncident(
                        response.getNumberPlate(),
                        type,
                        "Unknown Location"
                );

                alertLogService.logAlert(
                        response.getNumberPlate(),
                        type,
                        response.getHelpline()
                );

            } catch (RuntimeException ignored) {

            }

            List<IncidentReport> reports =
                    incidentReportService
                            .getReportsByVehicle(numberPlate);

            Map<String, Object> result =
                    new HashMap<>();

            result.put(
                    "vehicleResponse",
                    response != null
                            ? response
                            : "No registry intelligence found"
            );

            result.put("citizenReports", reports);

            result.put("reportCount", reports.size());

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(404)
                    .body("Vehicle not found");
        }
    }
}