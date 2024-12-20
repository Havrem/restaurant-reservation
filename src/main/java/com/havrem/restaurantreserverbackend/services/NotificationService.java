package com.havrem.restaurantreserverbackend.services;

import com.havrem.restaurantreserverbackend.models.EmailNotification;
import com.havrem.restaurantreserverbackend.other.Observer;
import com.havrem.restaurantreserverbackend.models.SmsNotification;
import com.havrem.restaurantreserverbackend.repositories.EmailNotificationRepository;
import com.havrem.restaurantreserverbackend.repositories.SmsNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements Observer {
    private final EmailNotificationRepository emailNotificationRepository;
    private final SmsNotificationRepository smsNotificationRepository;

    private final SmsService smsService;
    private final EmailService emailService;

    public NotificationService(SmsService smsService, EmailService emailService, EmailNotificationRepository emailNotificationRepository, SmsNotificationRepository smsNotificationRepository) {
        this.emailNotificationRepository = emailNotificationRepository;
        this.smsNotificationRepository = smsNotificationRepository;

        this.smsService = smsService;
        this.emailService = emailService;
    }

    public void saveSmsNotification(SmsNotification smsNotification) {
        smsNotificationRepository.save(smsNotification);
    }

    public void saveEmailNotification(EmailNotification emailNotification) {
        emailNotificationRepository.save(emailNotification);
    }

    @Override
    public void update(long id) {
        List<EmailNotification> emailNotifications = emailNotificationRepository.findAll();
        List<SmsNotification> smsNotifications = smsNotificationRepository.findAll();

        for (EmailNotification emailNotification : emailNotifications) {
            emailService.sendSimpleMail(emailNotification.getEmailDetails());
            emailNotificationRepository.delete(emailNotification);
        }

        for (SmsNotification smsNotification : smsNotifications) {
            smsService.sendSms(smsNotification.getSmsDetails());
            smsNotificationRepository.delete(smsNotification);
        }
    }
}
