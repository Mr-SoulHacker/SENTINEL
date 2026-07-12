package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.GuidanceResponse;
import com.sentinel.sentinel.model.IncidentCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuidanceService {

    public GuidanceResponse getGuidance(IncidentCategory category) {

        switch (category) {

            case THEFT:
                return new GuidanceResponse(
                        "HIGH",
                        "112",
                        "Nearest Police Station",
                        List.of(
                                "Ensure personal safety",
                                "Verify vehicle was not relocated",
                                "Record last known location and time"
                        ),
                        List.of(
                                "Vehicle registration number",
                                "RC copy",
                                "Insurance details",
                                "Vehicle photographs",
                                "Available CCTV footage"
                        ),
                        List.of(
                                "Call emergency helpline",
                                "Register FIR",
                                "Provide vehicle documents",
                                "Share available evidence"
                        )
                );

            default:
                return new GuidanceResponse(
                        "LOW",
                        "N/A",
                        "Unknown",
                        List.of("No immediate actions available"),
                        List.of("No evidence checklist available"),
                        List.of("No escalation procedure available")
                );
        }
    }
}