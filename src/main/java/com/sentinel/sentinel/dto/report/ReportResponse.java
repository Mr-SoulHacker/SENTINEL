package com.sentinel.sentinel.dto.report;

public class ReportResponse {

    private Long reportId;
    private String status;
    private String helpline;
    private String nextStep;

    public ReportResponse() {
    }

    public ReportResponse(Long reportId,
                          String status,
                          String helpline,
                          String nextStep) {

        this.reportId = reportId;
        this.status = status;
        this.helpline = helpline;
        this.nextStep = nextStep;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHelpline() {
        return helpline;
    }

    public void setHelpline(String helpline) {
        this.helpline = helpline;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }
}