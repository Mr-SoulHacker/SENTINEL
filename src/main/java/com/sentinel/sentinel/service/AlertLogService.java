package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.AlertLog;
import com.sentinel.sentinel.model.IncidentType;
import com.sentinel.sentinel.repository.AlertLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertLogService {

    private final AlertLogRepository alertLogRepository;

    public AlertLogService(AlertLogRepository alertLogRepository) {
        this.alertLogRepository = alertLogRepository;
    }

    public AlertLog logAlert(String vehicleNumber,
                             IncidentType category,
                             String sentTo) {

        AlertLog alertLog = new AlertLog(
                vehicleNumber,
                category,
                sentTo
        );

        return alertLogRepository.save(alertLog);
    }
}