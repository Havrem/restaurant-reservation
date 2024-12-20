package com.havrem.restaurantreserverbackend.models;

import jakarta.persistence.*;

@Entity
public class Reservation {
    @OneToOne
    private Timeslot timeslot;
    private String costumer;
    @Id
    @GeneratedValue
    private Long id;

    public Reservation(Timeslot timeslot, String costumer) {
        this.timeslot = timeslot;
        this.costumer = costumer;
    }

    public Reservation() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public String getCostumer() {
        return costumer;
    }
}
