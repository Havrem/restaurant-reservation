package com.havrem.restaurantreserverbackend.models;

import com.havrem.restaurantreserverbackend.other.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Timeslot {
    private LocalDateTime start;
    private LocalDateTime end;
    private int tableNumber;
    private Status status;
    @Id
    @GeneratedValue
    private Long id;

    public Timeslot(LocalDateTime start, LocalDateTime end, int tableNumber, Status status) {
        this.start = start;
        this.end = end;
        this.tableNumber = tableNumber;
        this.status = status;
    }

    public Timeslot() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
