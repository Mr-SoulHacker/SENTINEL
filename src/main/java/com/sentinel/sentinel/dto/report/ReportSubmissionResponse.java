package com.sentinel.sentinel.dto.report;

import com.sentinel.sentinel.model.GuidanceResponse;
import com.sentinel.sentinel.model.IncidentReport;

public class ReportSubmissionResponse {

    private IncidentReport report;
    private GuidanceResponse guidance;

    public ReportSubmissionResponse(
            IncidentReport report,
            GuidanceResponse guidance) {

        this.report = report;
        this.guidance = guidance;
    }

    public IncidentReport getReport() {
        return report;
    }

    public GuidanceResponse getGuidance() {
        return guidance;
    }
}
