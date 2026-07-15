package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.Incident;
import com.sentinel.sentinel.model.IncidentType;
import com.sentinel.sentinel.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public Incident save(Incident incident) {
        return incidentRepository.save(incident);
    }

    public Incident logIncident(String vehicleNumber,
                                IncidentType type,
                                String location) {

        Incident incident = new Incident(
                vehicleNumber,
                type,
                location
        );

        return incidentRepository.save(incident);
    }

    public List<Incident> getThefts() {
        return incidentRepository.findByType(IncidentType.THEFT);
    }

    public List<Incident> getAccidents() {
        return incidentRepository.findByType(IncidentType.ACCIDENT);
    }}