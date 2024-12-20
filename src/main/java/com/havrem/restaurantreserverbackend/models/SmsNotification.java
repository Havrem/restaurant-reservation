package com.havrem.restaurantreserverbackend.models;

import jakarta.persistence.*;

@Entity
public class SmsNotification {
    @OneToOne(cascade = CascadeType.ALL)
    private SmsDetails smsDetails;
    @Id
    @GeneratedValue
    private Long id;

    public SmsNotification(SmsDetails smsDetails) {
        this.smsDetails = smsDetails;
    }

    public SmsNotification() {
    }

    public SmsDetails getSmsDetails() {
        return smsDetails;
    }

    public void setSmsDetails(SmsDetails smsDetails) {
        this.smsDetails = smsDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
