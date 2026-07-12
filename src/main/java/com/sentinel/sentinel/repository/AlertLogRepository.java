package com.sentinel.sentinel.repository;

import com.sentinel.sentinel.model.AlertLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertLogRepository
        extends JpaRepository<AlertLog, Long> {
}