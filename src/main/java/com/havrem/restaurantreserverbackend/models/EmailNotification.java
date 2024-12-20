package com.havrem.restaurantreserverbackend.models;

import jakarta.persistence.*;

@Entity
public class EmailNotification {
    @OneToOne(cascade = CascadeType.ALL)
    private EmailDetails emailDetails;
    @Id
    @GeneratedValue
    private Long id;

    public EmailNotification(EmailDetails emailDetails) {
        this.emailDetails = emailDetails;
    }

    public EmailNotification() {

    }

    public EmailDetails getEmailDetails() {
        return emailDetails;
    }

    public void setEmailDetails(EmailDetails emailDetails) {
        this.emailDetails = emailDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
