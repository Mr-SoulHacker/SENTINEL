package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.IncidentCategory;
import com.sentinel.sentinel.model.IncidentReport;
import com.sentinel.sentinel.repository.IncidentReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentReportService {

    private final IncidentReportRepository repository;

    public IncidentReportService(IncidentReportRepository repository) {
        this.repository = repository;
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

        return repository.save(report);
    }

    public List<IncidentReport> getAllReports() {
        return repository.findAll();
    }

    public List<IncidentReport>
    getReportsByVehicle(String vehicleNumber) {

        return repository.findByVehicleNumber(vehicleNumber);
    }
}