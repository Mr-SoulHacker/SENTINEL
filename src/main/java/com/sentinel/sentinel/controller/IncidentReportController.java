package com.sentinel.sentinel.controller;

import com.sentinel.sentinel.dto.report.IncidentReportRequest;
import com.sentinel.sentinel.dto.report.ReportSubmissionResponse;
import com.sentinel.sentinel.model.GuidanceResponse;
import com.sentinel.sentinel.model.IncidentReport;
import com.sentinel.sentinel.service.GuidanceService;
import com.sentinel.sentinel.service.IncidentReportService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class IncidentReportController {

    private final IncidentReportService service;
    private final GuidanceService guidanceService;

    public IncidentReportController(
            IncidentReportService service,
            GuidanceService guidanceService) {

        this.service = service;
        this.guidanceService = guidanceService;
    }

    @PostMapping
    public ReportSubmissionResponse createReport(
            @RequestBody IncidentReportRequest request,
            Authentication authentication) {

        long start = System.currentTimeMillis();

        System.out.println("CONTROLLER DEBUG: POST /reports started");

        String reportedBy = authentication.getName();

        System.out.println(
                "CONTROLLER DEBUG: authenticated user = " + reportedBy
        );

        long reportStart = System.currentTimeMillis();

        IncidentReport report = service.createReport(
                request.getVehicleNumber(),
                request.getCategory(),
                request.getDescription(),
                request.getLocation(),
                reportedBy
        );

        System.out.println(
                "CONTROLLER DEBUG: createReport service completed in "
                        + (System.currentTimeMillis() - reportStart)
                        + " ms"
        );

        long guidanceStart = System.currentTimeMillis();

        GuidanceResponse guidance =
                guidanceService.getGuidance(request.getCategory());

        System.out.println(
                "CONTROLLER DEBUG: guidance completed in "
                        + (System.currentTimeMillis() - guidanceStart)
                        + " ms"
        );

        ReportSubmissionResponse response =
                new ReportSubmissionResponse(
                        report,
                        guidance
                );

        System.out.println(
                "CONTROLLER DEBUG: returning HTTP response after "
                        + (System.currentTimeMillis() - start)
                        + " ms"
        );

        return response;
    }

    @GetMapping
    public List<IncidentReport> getAllReports() {
        return service.getAllReports();
    }

    @GetMapping("/{id}")
    public IncidentReport getReportById(
            @PathVariable Long id) {

        return service.getReportById(id);
    }

    @GetMapping("/vehicle/{vehicleNumber}")
    public List<IncidentReport> getReportsByVehicle(
            @PathVariable String vehicleNumber) {

        return service.getReportsByVehicle(vehicleNumber);
    }

    @PutMapping("/{id}/investigate")
    public IncidentReport investigate(
            @PathVariable Long id) {

        return service.startInvestigation(id);
    }

    @PutMapping("/{id}/resolve")
    public IncidentReport resolve(
            @PathVariable Long id) {

        return service.resolveReport(id);
    }
}