package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.IncidentReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String adminEmail;
    private final String senderEmail;

    public EmailService(
            JavaMailSender mailSender,
            @Value("${sentinel.admin.email}") String adminEmail,
            @Value("${spring.mail.username}") String senderEmail) {

        this.mailSender = mailSender;
        this.adminEmail = adminEmail;
        this.senderEmail = senderEmail;
    }

    @Async
    public void sendAdminReportEmail(IncidentReport report) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(senderEmail);
            message.setTo(adminEmail);

            message.setSubject(
                    "SENTINEL - New Incident Report #" + report.getId()
            );

            message.setText(
                    "SENTINEL - NEW INCIDENT REPORT\n\n" +

                            "Report ID: " + report.getId() + "\n" +
                            "Reported By: " + report.getReportedBy() + "\n" +
                            "Vehicle Number: " + report.getVehicleNumber() + "\n" +
                            "Category: " + report.getCategory() + "\n" +
                            "Description: " + report.getDescription() + "\n" +
                            "Location: " + report.getLocation() + "\n" +
                            "Status: " + report.getStatus() + "\n" +
                            "Reported At: " + report.getReportedAt()
            );

            mailSender.send(message);

            System.out.println(
                    "EMAIL: Admin notification sent for report #"
                            + report.getId()
            );

        } catch (Exception e) {

            System.err.println(
                    "EMAIL: Failed to send admin email: "
                            + e.getMessage()
            );
        }
    }

    @Async
    public void sendCitizenConfirmationEmail(
            String citizenEmail,
            IncidentReport report) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(senderEmail);
            message.setTo(citizenEmail);

            message.setSubject(
                    "SENTINEL - Report Submitted Successfully"
            );

            message.setText(
                    "Your incident report has been submitted successfully.\n\n" +

                            "Report ID: " + report.getId() + "\n" +
                            "Vehicle Number: " + report.getVehicleNumber() + "\n" +
                            "Category: " + report.getCategory() + "\n" +
                            "Status: " + report.getStatus() + "\n\n" +

                            "Please keep your Report ID to track the status " +
                            "of your report in the SENTINEL app.\n\n" +

                            "SENTINEL\n" +
                            "Citizen Safety and Incident Reporting"
            );

            mailSender.send(message);

            System.out.println(
                    "EMAIL: Citizen confirmation sent to "
                            + citizenEmail
                            + " for report #"
                            + report.getId()
            );

        } catch (Exception e) {

            System.err.println(
                    "EMAIL: Failed to send citizen email to "
                            + citizenEmail
                            + ": "
                            + e.getMessage()
            );
        }
    }
}