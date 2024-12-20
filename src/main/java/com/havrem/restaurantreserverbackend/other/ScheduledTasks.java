package com.havrem.restaurantreserverbackend.other;

import com.havrem.restaurantreserverbackend.models.DailyRapport;
import com.havrem.restaurantreserverbackend.models.Restaurant;
import com.havrem.restaurantreserverbackend.services.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {
    private RestaurantService restaurantService;
    private TimeslotFactory timeslotFactory;
    //1. Get all restaurants
    //2. Get the daily rapport
    //3. Save it to list and persist it to database
    //4. Wipe daily rapport/create new daily rapport.-

    public ScheduledTasks(RestaurantService restaurantService, TimeslotFactory timeslotFactory) {
        this.restaurantService = restaurantService;
        this.timeslotFactory = timeslotFactory;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    @Transactional
    public void dailyRapportGenerator(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        for (Restaurant restaurant : restaurants) {
//          //Create rapport based on existing state of restaurant
            DailyRapport dailyRapport = new DailyRapport(restaurant.getTimeslots().size(), LocalDateTime.now(), restaurant.getReservations(), restaurant.getBooked());

            List<DailyRapport> history = restaurant.getHistory();
            history.add(dailyRapport);

            //Reset all relevant values of restaurant
            restaurant.setReservations(new ArrayList<>());
            restaurant.setTimeslots(timeslotFactory.generateTimeslots(restaurant.getTables()));
            restaurant.setBooked(0);

            //Save reset restaurant
            restaurantService.saveRestaurant(restaurant);
        }
    }
}
