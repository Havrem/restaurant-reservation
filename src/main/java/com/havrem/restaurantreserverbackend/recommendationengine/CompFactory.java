package com.havrem.restaurantreserverbackend.recommendationengine;

import com.havrem.restaurantreserverbackend.models.DailyRapport;
import com.havrem.restaurantreserverbackend.models.Restaurant;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class CompFactory {
    public Comparator<Restaurant> createByBookingPercentageUpToSevenDaysBack() {
        return (Restaurant r1, Restaurant r2) -> {
            List<DailyRapport> r1History = r1.getHistory();
            List<DailyRapport> r2History = r2.getHistory();

            List<DailyRapport> historyUpToSevenDaysBackR1 = r1History.stream().filter(dr ->
                dr.getDate().isAfter(LocalDateTime.now().minusDays(7))
            ).toList();
            List<DailyRapport> historyUpToSevenDaysBackR2 = r2History.stream().filter(dr ->
                    dr.getDate().isAfter(LocalDateTime.now().minusDays(7))
            ).toList();

            int r1BookingPercentage = calculateBookingPercentage(historyUpToSevenDaysBackR1);
            int r2BookingPercentage = calculateBookingPercentage(historyUpToSevenDaysBackR2);

            return Integer.compare(r1BookingPercentage, r2BookingPercentage);
        };
    }

    private int calculateBookingPercentage(List<DailyRapport> rapports) {
        int bookedSum = 0;
        int timeslotsSum = 0;

        for (DailyRapport dr : rapports) {
            bookedSum += dr.getBooked();
            timeslotsSum += dr.getNrOfTimeslots();
        }

        return bookedSum / timeslotsSum;
    }
}
