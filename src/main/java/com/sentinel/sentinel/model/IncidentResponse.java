package com.sentinel.sentinel.model;

public class IncidentResponse {

    private String numberPlate;
    private String incidentType;
    private String helpline;
    private String nextStep;

    public IncidentResponse(String numberPlate,
                            String incidentType,
                            String helpline,
                            String nextStep) {
        this.numberPlate = numberPlate;
        this.incidentType = incidentType;
        this.helpline = helpline;
        this.nextStep = nextStep;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getHelpline() {
        return helpline;
    }

    public String getNextStep() {
        return nextStep;
    }
}
