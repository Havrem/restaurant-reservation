package com.havrem.restaurantreserverbackend.recommendationengine.strategies;

import com.havrem.restaurantreserverbackend.models.Restaurant;
import com.havrem.restaurantreserverbackend.recommendationengine.CompFactory;
import com.havrem.restaurantreserverbackend.recommendationengine.RecommendationStrategy;
import com.havrem.restaurantreserverbackend.services.RestaurantService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class Popular implements RecommendationStrategy {
    private final RestaurantService restaurantService;
    private final CompFactory compFactory;

    public Popular(RestaurantService restaurantService, CompFactory compFactory) {
        this.restaurantService = restaurantService;
        this.compFactory = compFactory;
    }

    @Override
    public List<Restaurant> execute() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants(); //Get all restaurants
        List<Restaurant> selected = new ArrayList<>(); //Recommendation lists

        Comparator<Restaurant> comparator = compFactory.createByBookingPercentageUpToSevenDaysBack();
        restaurants.sort(comparator);

        for(int i = 0 ; i < 10; i++) {
            selected.add(restaurants.get(i));
        }

        return selected;
    }
}
