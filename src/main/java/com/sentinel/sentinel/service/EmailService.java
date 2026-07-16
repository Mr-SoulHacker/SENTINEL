package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.IncidentReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EmailService {

    private static final String BREVO_API_URL =
            "https://api.brevo.com/v3/smtp/email";

    private static final String SENDER_EMAIL =
            "app.sentinel.support@gmail.com";

    private final String brevoApiKey;
    private final String adminEmail;
    private final HttpClient httpClient;

    public EmailService(
            @Value("${brevo.api.key}") String brevoApiKey,
            @Value("${sentinel.admin.email}") String adminEmail) {

        this.brevoApiKey = brevoApiKey;
        this.adminEmail = adminEmail;
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

            String jsonBody =
                    "{" +
                            "\"sender\":{" +
                            "\"name\":\"SENTINEL\"," +
                            "\"email\":\"" + escapeJson(SENDER_EMAIL) + "\"" +
                            "}," +
                            "\"to\":[{" +
                            "\"email\":\"" + escapeJson(recipient) + "\"" +
                            "}]," +
                            "\"subject\":\"" + escapeJson(subject) + "\"," +
                            "\"htmlContent\":\"" + escapeJson(html) + "\"" +
                            "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BREVO_API_URL))
                    .header("accept", "application/json")
                    .header("api-key", brevoApiKey)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
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

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}