package com.sentinel.sentinel.repository;

import com.sentinel.sentinel.model.ScanLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScanLogRepository extends JpaRepository<ScanLog, Long> {

    List<ScanLog> findByVehicleNumber(String vehicleNumber);
}
    