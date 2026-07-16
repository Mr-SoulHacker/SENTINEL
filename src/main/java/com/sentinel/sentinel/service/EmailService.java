package com.sentinel.sentinel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sentinel.sentinel.model.IncidentReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private final String brevoApiKey;
    private final String adminEmail;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BREVO_API_URL =
            "https://api.brevo.com/v3/smtp/email";

    private static final String SENDER_EMAIL =
            "app.sentinel.support@gmail.com";

    public EmailService(
            @Value("${brevo.api.key}") String brevoApiKey,
            @Value("${sentinel.admin.email}") String adminEmail,
            ObjectMapper objectMapper) {

        this.brevoApiKey = brevoApiKey;
        this.adminEmail = adminEmail;
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Async
    public void sendAdminReportEmail(IncidentReport report) {

        String subject =
                "SENTINEL - New Incident Report #" + report.getId();

        String html =
                "<h2>SENTINEL - New Incident Report</h2>" +
                        "<p><strong>Report ID:</strong> " + report.getId() + "</p>" +
                        "<p><strong>Reported By:</strong> " + report.getReportedBy() + "</p>" +
                        "<p><strong>Vehicle Number:</strong> " + report.getVehicleNumber() + "</p>" +
                        "<p><strong>Category:</strong> " + report.getCategory() + "</p>" +
                        "<p><strong>Description:</strong> " + report.getDescription() + "</p>" +
                        "<p><strong>Location:</strong> " + report.getLocation() + "</p>" +
                        "<p><strong>Status:</strong> " + report.getStatus() + "</p>" +
                        "<p><strong>Reported At:</strong> " + report.getReportedAt() + "</p>";

        sendEmail(
                adminEmail,
                subject,
                html,
                "admin",
                report.getId()
        );
    }

    @Async
    public void sendCitizenConfirmationEmail(
            String citizenEmail,
            IncidentReport report) {

        String subject =
                "SENTINEL - Report Submitted Successfully";

        String html =
                "<h2>Your incident report has been submitted</h2>" +
                        "<p>Your report was successfully received by SENTINEL.</p>" +
                        "<p><strong>Report ID:</strong> " + report.getId() + "</p>" +
                        "<p><strong>Vehicle Number:</strong> " + report.getVehicleNumber() + "</p>" +
                        "<p><strong>Category:</strong> " + report.getCategory() + "</p>" +
                        "<p><strong>Status:</strong> " + report.getStatus() + "</p>" +
                        "<p>Please keep your Report ID to track your report in the SENTINEL app.</p>";

        sendEmail(
                citizenEmail,
                subject,
                html,
                "citizen",
                report.getId()
        );
    }

    private void sendEmail(
            String recipient,
            String subject,
            String html,
            String emailType,
            Long reportId) {

        try {

            Map<String, Object> payload = Map.of(
                    "sender", Map.of(
                            "name", "SENTINEL",
                            "email", SENDER_EMAIL
                    ),
                    "to", List.of(
                            Map.of("email", recipient)
                    ),
                    "subject", subject,
                    "htmlContent", html
            );

            String requestBody =
                    objectMapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BREVO_API_URL))
                    .header("accept", "application/json")
                    .header("api-key", brevoApiKey)
                    .header("content-type", "application/json")
                    .POST(
                            HttpRequest.BodyPublishers.ofString(requestBody)
                    )
                    .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() >= 200 &&
                    response.statusCode() < 300) {

                System.out.println(
                        "BREVO: " + emailType +
                                " email sent for report #" +
                                reportId
                );

            } else {

                System.err.println(
                        "BREVO: Failed to send " +
                                emailType +
                                " email. HTTP " +
                                response.statusCode() +
                                " - " +
                                response.body()
                );
            }

        } catch (Exception e) {

            System.err.println(
                    "BREVO: Failed to send " +
                            emailType +
                            " email: " +
                            e.getMessage()
            );
        }
    }
}