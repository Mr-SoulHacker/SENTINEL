package com.sentinel.sentinel.model;

import java.util.List;

public class GuidanceResponse {

    private String severity;

    private String primaryHelpline;

    private String authority;

    private List<String> immediateActions;

    private List<String> evidenceChecklist;

    private List<String> escalationProcedure;

    public GuidanceResponse(
            String severity,
            String primaryHelpline,
            String authority,
            List<String> immediateActions,
            List<String> evidenceChecklist,
            List<String> escalationProcedure) {

        this.severity = severity;
        this.primaryHelpline = primaryHelpline;
        this.authority = authority;
        this.immediateActions = immediateActions;
        this.evidenceChecklist = evidenceChecklist;
        this.escalationProcedure = escalationProcedure;
    }

    public String getSeverity() {
        return severity;
    }

    public String getPrimaryHelpline() {
        return primaryHelpline;
    }

    public String getAuthority() {
        return authority;
    }

    public List<String> getImmediateActions() {
        return immediateActions;
    }

    public List<String> getEvidenceChecklist() {
        return evidenceChecklist;
    }

    public List<String> getEscalationProcedure() {
        return escalationProcedure;
    }
}