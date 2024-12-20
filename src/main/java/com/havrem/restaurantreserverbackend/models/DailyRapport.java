package com.havrem.restaurantreserverbackend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DailyRapport {
    private LocalDateTime date;
    private int nrOfTimeslots;
    private int booked;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    @Id
    @GeneratedValue
    private Long id;

    public DailyRapport(int nrOfTimeslots, LocalDateTime date, List<Reservation> reservations, int booked) {
        this.nrOfTimeslots = nrOfTimeslots;
        this.date = date;
        this.reservations = reservations;
        this.booked = booked;
    }

    public DailyRapport() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getNrOfTimeslots() {
        return nrOfTimeslots;
    }

    public int getBooked() {
        return booked;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
