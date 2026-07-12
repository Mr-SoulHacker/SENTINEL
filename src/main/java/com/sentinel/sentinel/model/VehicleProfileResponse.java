package com.sentinel.sentinel.model;

public class VehicleProfileResponse {

    private String numberPlate;
    private String ownerName;
    private String state;
    private String vehicleType;
    private String rcStatus;
    private String insuranceStatus;
    private int reportCount;

    public VehicleProfileResponse(
            String numberPlate,
            String ownerName,
            String state,
            String vehicleType,
            String rcStatus,
            String insuranceStatus,
            int reportCount) {

        this.numberPlate = numberPlate;
        this.ownerName = ownerName;
        this.state = state;
        this.vehicleType = vehicleType;
        this.rcStatus = rcStatus;
        this.insuranceStatus = insuranceStatus;
        this.reportCount = reportCount;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getState() {
        return state;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getRcStatus() {
        return rcStatus;
    }

    public String getInsuranceStatus() {
        return insuranceStatus;
    }

    public int getReportCount() {
        return reportCount;
    }
}