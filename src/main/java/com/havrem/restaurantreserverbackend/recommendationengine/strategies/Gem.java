package com.havrem.restaurantreserverbackend.recommendationengine.strategies;

import com.havrem.restaurantreserverbackend.models.Restaurant;
import com.havrem.restaurantreserverbackend.recommendationengine.RecommendationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Gem implements RecommendationStrategy {
    @Override
    public List<Restaurant> execute() {
        return List.of();
    }
}
