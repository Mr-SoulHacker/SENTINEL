package com.sentinel.sentinel.controller;

import com.sentinel.sentinel.model.IncidentCategory;
import com.sentinel.sentinel.model.IncidentReport;
import com.sentinel.sentinel.service.IncidentReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final IncidentReportService reportService;

    public ReportController(IncidentReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<?> createReport(

            @RequestParam String vehicleNumber,
            @RequestParam IncidentCategory category,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam String reportedBy
    ) {

        IncidentReport report =
                reportService.createReport(
                        vehicleNumber,
                        category,
                        description,
                        location,
                        reportedBy
                );

        return ResponseEntity.ok(report);
    }
}