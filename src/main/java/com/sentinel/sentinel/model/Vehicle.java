package com.sentinel.sentinel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehicle {

    @Id
    private String numberPlate;

    private String ownerName;
    private String state;

    private String vehicleType;
    private String rcStatus;
    private String insuranceStatus;

    private boolean stolen;
    private boolean accidentReported;

    public Vehicle() {
    }

    // OLD CONSTRUCTOR (for existing project code)
    public Vehicle(
            String numberPlate,
            String ownerName,
            String state,
            boolean stolen,
            boolean accidentReported) {

        this.numberPlate = numberPlate;
        this.ownerName = ownerName;
        this.state = state;
        this.stolen = stolen;
        this.accidentReported = accidentReported;

        this.vehicleType = "UNKNOWN";
        this.rcStatus = "ACTIVE";
        this.insuranceStatus = "UNKNOWN";
    }

    // NEW CONSTRUCTOR
    public Vehicle(
            String numberPlate,
            String ownerName,
            String state,
            String vehicleType,
            String rcStatus,
            String insuranceStatus,
            boolean stolen,
            boolean accidentReported) {

        this.numberPlate = numberPlate;
        this.ownerName = ownerName;
        this.state = state;
        this.vehicleType = vehicleType;
        this.rcStatus = rcStatus;
        this.insuranceStatus = insuranceStatus;
        this.stolen = stolen;
        this.accidentReported = accidentReported;
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

    public boolean isStolen() {
        return stolen;
    }

    public boolean isAccidentReported() {
        return accidentReported;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setRcStatus(String rcStatus) {
        this.rcStatus = rcStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public void setStolen(boolean stolen) {
        this.stolen = stolen;
    }

    public void setAccidentReported(boolean accidentReported) {
        this.accidentReported = accidentReported;
    }
}