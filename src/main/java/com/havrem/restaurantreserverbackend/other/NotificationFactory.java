package com.havrem.restaurantreserverbackend.other;

import com.havrem.restaurantreserverbackend.models.EmailDetails;
import com.havrem.restaurantreserverbackend.models.EmailNotification;
import com.havrem.restaurantreserverbackend.models.SmsDetails;
import com.havrem.restaurantreserverbackend.models.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    public EmailNotification createEmailNotification(String recipient, String subject, String body) {
        return new EmailNotification(new EmailDetails(recipient, subject, body));
    }

    public SmsNotification createSmsNotification(String phoneNumber, String message) {
        return new SmsNotification(new SmsDetails(phoneNumber, message));
    }
}
