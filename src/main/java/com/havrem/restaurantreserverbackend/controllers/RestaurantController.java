package com.havrem.restaurantreserverbackend.controllers;

import com.havrem.restaurantreserverbackend.other.Preference;
import com.havrem.restaurantreserverbackend.models.Restaurant;
import com.havrem.restaurantreserverbackend.services.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> retrieveAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{preference}")
    public List<Restaurant> retrieveByPreference(@PathVariable Preference preference) {
        return restaurantService.getRestaurantsByPreference(preference);
    }

    @PostMapping("/restaurants/{id}/reserve/{email}")
    public void reserveTable(@PathVariable long id, @PathVariable String email) {
        System.out.println("Inside endpoint");
        restaurantService.reserveTable(id, email);
    }

    @PostMapping("/restaurants/{id}/waitlist/email/{email}")
    public void addEmailNotification(@PathVariable long id, @PathVariable String email) {
        restaurantService.addEmailNotification(id, email);
    }

    @PostMapping("/restaurants/{id}/waitlist/sms/{number}")
    public void addSmsNotification(@PathVariable long id, @PathVariable String number) {
        restaurantService.addSmsNotification(id, number);
    }

    @PostMapping("/restaurants/{id}/add")
    public void addTable(@PathVariable long id) {
        restaurantService.addTable(id);
    }
}
