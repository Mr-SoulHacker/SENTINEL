package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.IncidentReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${sentinel.admin.email}")
    private String adminEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAdminReportEmail(IncidentReport report) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(adminEmail);
        message.setSubject(
                "SENTINEL - New Incident Report #" + report.getId()
        );

        message.setText(
                "A new incident report has been submitted.\n\n" +
                        "Report ID: " + report.getId() + "\n" +
                        "Reported By: " + report.getReportedBy() + "\n" +
                        "Vehicle Number: " + report.getVehicleNumber() + "\n" +
                        "Category: " + report.getCategory() + "\n" +
                        "Description: " + report.getDescription() + "\n" +
                        "Location: " + report.getLocation() + "\n" +
                        "Status: " + report.getStatus()
        );

        mailSender.send(message);
    }

    public void sendCitizenConfirmationEmail(
            String citizenEmail,
            IncidentReport report) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(citizenEmail);
        message.setSubject(
                "SENTINEL - Report Submitted Successfully"
        );

        message.setText(
                "Your incident report has been successfully submitted.\n\n" +
                        "Report ID: " + report.getId() + "\n" +
                        "Status: " + report.getStatus() + "\n\n" +
                        "Please keep your Report ID to track your report in the SENTINEL app."
        );

        mailSender.send(message);
    }
}