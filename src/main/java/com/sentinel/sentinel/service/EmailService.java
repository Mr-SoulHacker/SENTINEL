package com.sentinel.sentinel.service;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.sentinel.sentinel.model.IncidentReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    @Value("${sentinel.admin.email}")
    private String adminEmail;

    public EmailService(
            @Value("${resend.api.key}") String resendApiKey) {

        this.resend = new Resend(resendApiKey);
    }

    @Async
    public void sendAdminReportEmail(IncidentReport report) {

        try {

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("SENTINEL <onboarding@resend.dev>")
                    .to(adminEmail)
                    .subject("SENTINEL - New Incident Report #" + report.getId())
                    .html(
                            "<h2>New Incident Report</h2>" +
                                    "<p><strong>Report ID:</strong> " + report.getId() + "</p>" +
                                    "<p><strong>Reported By:</strong> " + report.getReportedBy() + "</p>" +
                                    "<p><strong>Vehicle Number:</strong> " + report.getVehicleNumber() + "</p>" +
                                    "<p><strong>Category:</strong> " + report.getCategory() + "</p>" +
                                    "<p><strong>Description:</strong> " + report.getDescription() + "</p>" +
                                    "<p><strong>Location:</strong> " + report.getLocation() + "</p>" +
                                    "<p><strong>Status:</strong> " + report.getStatus() + "</p>"
                    )
                    .build();

            resend.emails().send(params);

            System.out.println(
                    "RESEND: Admin notification sent for report #"
                            + report.getId()
            );

        } catch (Exception e) {

            System.err.println(
                    "RESEND: Failed to send admin email: "
                            + e.getMessage()
            );
        }
    }

    @Async
    public void sendCitizenConfirmationEmail(
            String citizenEmail,
            IncidentReport report) {

        try {

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("SENTINEL <onboarding@resend.dev>")
                    .to(citizenEmail)
                    .subject("SENTINEL - Report Submitted Successfully")
                    .html(
                            "<h2>Your incident report has been submitted</h2>" +
                                    "<p><strong>Report ID:</strong> " + report.getId() + "</p>" +
                                    "<p><strong>Status:</strong> " + report.getStatus() + "</p>" +
                                    "<p>Please keep your Report ID to track your report in the SENTINEL app.</p>"
                    )
                    .build();

            resend.emails().send(params);

            System.out.println(
                    "RESEND: Citizen confirmation sent for report #"
                            + report.getId()
            );

        } catch (Exception e) {

            System.err.println(
                    "RESEND: Failed to send citizen email: "
                            + e.getMessage()
            );
        }
    }
}