package com.havrem.restaurantreserverbackend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Waitlist {
    @OneToMany(cascade = CascadeType.ALL)
    private List<EmailNotification> emailNotifications;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SmsNotification> smsNotifications;
    @Id
    @GeneratedValue
    private Long id;

    public Waitlist() {
        emailNotifications = new ArrayList<>();
        smsNotifications = new ArrayList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void addEmailNotification(EmailNotification emailNotification) {
        emailNotifications.add(emailNotification);
    }

    public void addSmsNotification(SmsNotification smsNotification) {
        smsNotifications.add(smsNotification);
    }

    public List<EmailNotification> getEmailNotifications() {
        return emailNotifications;
    }

    public List<SmsNotification> getSmsNotifications() {
        return smsNotifications;
    }
}
