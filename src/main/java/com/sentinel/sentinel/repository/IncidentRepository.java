package com.sentinel.sentinel.repository;

import com.sentinel.sentinel.model.Incident;
import com.sentinel.sentinel.model.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findByType(IncidentType type);

    List<Incident> findByVehicleNumber(String vehicleNumber);
}