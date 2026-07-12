package com.sentinel.sentinel.model;

import java.util.List;

public class VehicleHistoryResponse {

    private Vehicle vehicle;

    private List<IncidentReport> reports;

    public VehicleHistoryResponse(
            Vehicle vehicle,
            List<IncidentReport> reports) {

        this.vehicle = vehicle;
        this.reports = reports;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<IncidentReport> getReports() {
        return reports;
    }
}