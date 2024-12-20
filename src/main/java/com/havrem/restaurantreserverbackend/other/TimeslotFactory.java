package com.havrem.restaurantreserverbackend.other;

import com.havrem.restaurantreserverbackend.models.Timeslot;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeslotFactory {
    //Generates a list of 4 timeslots per table
    public List<Timeslot> generateTimeslots(int tables) {
        LocalDate date = LocalDate.now();

        LocalTime firstTime = LocalTime.of(12,0,0);
        LocalTime secondTime = LocalTime.of(14,0,0);
        LocalTime thirdTime = LocalTime.of(16,0,0);
        LocalTime fourthTime = LocalTime.of(18,0,0);

        LocalDateTime firstDateTime = LocalDateTime.of(date, firstTime);
        LocalDateTime secondDateTime = LocalDateTime.of(date, secondTime);
        LocalDateTime thirdDateTime = LocalDateTime.of(date, thirdTime);
        LocalDateTime fourthDateTime = LocalDateTime.of(date, fourthTime);

        List<Timeslot> timeslots = new ArrayList<>();

        for (int i = 0; i < tables; i++) {
            timeslots.add(new Timeslot(firstDateTime, firstDateTime.plusHours(2), i, Status.AVAILABLE));
            timeslots.add(new Timeslot(secondDateTime, secondDateTime.plusHours(2), i, Status.AVAILABLE));
            timeslots.add(new Timeslot(thirdDateTime, thirdDateTime.plusHours(2), i, Status.AVAILABLE));
            timeslots.add(new Timeslot(fourthDateTime, fourthDateTime.plusHours(2), i, Status.AVAILABLE));
        }

        return timeslots;
    }
}
