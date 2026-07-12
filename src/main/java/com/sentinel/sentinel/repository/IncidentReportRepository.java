package com.sentinel.sentinel.repository;

import com.sentinel.sentinel.model.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentReportRepository
        extends JpaRepository<IncidentReport, Long> {

    List<IncidentReport>
    findByVehicleNumber(String vehicleNumber);

    long countByVehicleNumber(String vehicleNumber);

    List<IncidentReport>
    findByVehicleNumberOrderByReportedAtDesc(
            String vehicleNumber
    );
}