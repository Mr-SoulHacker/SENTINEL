package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.IncidentCategory;
import com.sentinel.sentinel.model.IncidentReport;
import com.sentinel.sentinel.model.ReportStatus;
import com.sentinel.sentinel.model.User;
import com.sentinel.sentinel.repository.IncidentReportRepository;
import com.sentinel.sentinel.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentReportService {

    private final IncidentReportRepository repository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public IncidentReportService(
            IncidentReportRepository repository,
            UserRepository userRepository,
            EmailService emailService) {

        this.repository = repository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public IncidentReport createReport(
            String vehicleNumber,
            IncidentCategory category,
            String description,
            String location,
            String reportedBy
    ) {

        IncidentReport report = new IncidentReport(
                vehicleNumber,
                category,
                description,
                location,
                reportedBy
        );

        // Save the report first
        IncidentReport savedReport = repository.save(report);

        // Find the citizen using the authenticated username
        User citizen = userRepository.findByUsername(reportedBy)
                .orElseThrow(() ->
                        new RuntimeException("Citizen account not found"));

        // Send full incident report to SENTINEL admin
        emailService.sendAdminReportEmail(savedReport);

        // Send submission confirmation to citizen
        emailService.sendCitizenConfirmationEmail(
                citizen.getEmail(),
                savedReport
        );

        return savedReport;
    }

    public List<IncidentReport> getAllReports() {
        return repository.findAll();
    }

    public IncidentReport getReportById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Report not found"));
    }

    public List<IncidentReport> getReportsByVehicle(String vehicleNumber) {
        return repository.findByVehicleNumber(vehicleNumber);
    }

    public IncidentReport startInvestigation(Long id) {

        IncidentReport report = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Report not found"));

        report.setStatus(ReportStatus.UNDER_INVESTIGATION);

        return repository.save(report);
    }

    public IncidentReport resolveReport(Long id) {

        IncidentReport report = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Report not found"));

        report.setStatus(ReportStatus.RESOLVED);

        return repository.save(report);
    }
}