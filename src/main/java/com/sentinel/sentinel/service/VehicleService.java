package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.IncidentReport;
import com.sentinel.sentinel.model.IncidentResponse;
import com.sentinel.sentinel.model.IncidentType;
import com.sentinel.sentinel.model.Vehicle;
import com.sentinel.sentinel.model.VehicleHistoryResponse;
import com.sentinel.sentinel.model.VehicleProfileResponse;
import com.sentinel.sentinel.repository.IncidentReportRepository;
import com.sentinel.sentinel.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository repository;
    private final IncidentReportRepository reportRepository;

    public VehicleService(
            VehicleRepository repository,
            IncidentReportRepository reportRepository) {

        this.repository = repository;
        this.reportRepository = reportRepository;
    }

    public Optional<IncidentType> determineIncident(
            String numberPlate) {

        Optional<Vehicle> vehicle = repository.findById(numberPlate);

        if (vehicle.isEmpty()) {
            return Optional.empty();
        }

        Vehicle v = vehicle.get();

        if (v.isStolen()) {
            return Optional.of(IncidentType.THEFT);
        }

        if (v.isAccidentReported()) {
            return Optional.of(IncidentType.ACCIDENT);
        }

        return Optional.of(IncidentType.NORMAL);
    }

    public IncidentResponse buildIncidentResponse(
            String numberPlate) {

        Vehicle vehicle = repository.findById(numberPlate)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        IncidentType type;

        if (vehicle.isStolen()) {
            type = IncidentType.THEFT;
        } else if (vehicle.isAccidentReported()) {
            type = IncidentType.ACCIDENT;
        } else {
            type = IncidentType.NORMAL;
        }

        String helpline;
        String nextStep;

        switch (type) {

            case THEFT -> {
                helpline = "100";
                nextStep = "Contact nearest police station";
            }

            case ACCIDENT -> {
                helpline = "108";
                nextStep = "Dispatch emergency services";
            }

            default -> {
                helpline = "N/A";
                nextStep = "No action required";
            }
        }

        return new IncidentResponse(
                vehicle.getNumberPlate(),
                type.name(),
                helpline,
                nextStep
        );
    }

    public VehicleProfileResponse getVehicleProfile(
            String numberPlate) {

        Vehicle vehicle = repository.findById(numberPlate)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        long reportCount =
                reportRepository.countByVehicleNumber(numberPlate);

        return new VehicleProfileResponse(

                vehicle.getNumberPlate(),

                vehicle.getOwnerName(),

                vehicle.getState(),

                vehicle.getVehicleType(),

                vehicle.getRcStatus(),

                vehicle.getInsuranceStatus(),

                (int) reportCount
        );
    }

    public VehicleHistoryResponse getVehicleHistory(
            String numberPlate) {

        Vehicle vehicle = repository.findById(numberPlate)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        List<IncidentReport> reports =
                reportRepository.findByVehicleNumberOrderByReportedAtDesc(
                        numberPlate
                );

        return new VehicleHistoryResponse(
                vehicle,
                reports
        );
    }

    public Iterable<Vehicle> getAllVehicles() {
        return repository.findAll();
    }
}