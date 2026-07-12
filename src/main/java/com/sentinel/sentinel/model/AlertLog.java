package com.sentinel.sentinel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_log")
public class AlertLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    private IncidentType category;

    private String sentTo;

    private LocalDateTime sentAt = LocalDateTime.now();

    public AlertLog() {
    }

    public AlertLog(String vehicleNumber,
                    IncidentType category,
                    String sentTo) {

        this.vehicleNumber = vehicleNumber;
        this.category = category;
        this.sentTo = sentTo;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public IncidentType getCategory() {
        return category;
    }

    public void setCategory(IncidentType category) {
        this.category = category;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}