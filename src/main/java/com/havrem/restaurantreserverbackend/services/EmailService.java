package com.havrem.restaurantreserverbackend.services;

import com.havrem.restaurantreserverbackend.models.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("{spring.mail.username}") private String sender;

    public EmailService(JavaMailSender mailSender) { //Constructor dependency injection
        this.mailSender = mailSender;
    }


    public void sendSimpleMail(EmailDetails emailDetails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(emailDetails.getRecipient());
        mailMessage.setSubject(emailDetails.getSubject());
        mailMessage.setText(emailDetails.getBody());

        mailSender.send(mailMessage);
    }
}
