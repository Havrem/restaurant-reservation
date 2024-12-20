package com.havrem.restaurantreserverbackend.models;

import com.havrem.restaurantreserverbackend.other.Preference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int tables;
    @Enumerated(EnumType.STRING)
    private Preference preference;
    @OneToOne(cascade = CascadeType.ALL)
    private Waitlist waitlist;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DailyRapport> history;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Timeslot> timeslots;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    private int booked;

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public Restaurant(String name, int tables, Preference preference) {
        this.name = name;
        this.tables = tables;
        this.preference = preference;
        waitlist = new Waitlist();
        timeslots = new ArrayList<>();
        history = new ArrayList<>();
        booked = 0;
    }

    public Restaurant() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public void addToWaitlist(SmsNotification notification) {
        waitlist.addSmsNotification(notification);
    }

    public void addToWaitlist(EmailNotification notification) {
        waitlist.addEmailNotification(notification);
    }

    public Waitlist getWaitlist() {
        return waitlist;
    }

    public List<DailyRapport> getHistory() {
        return history;
    }

    public List<Timeslot> getTimeslots() {
        return timeslots;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public void setWaitlist(Waitlist waitlist) {
        this.waitlist = waitlist;
    }

    public void setTimeslots(List<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
