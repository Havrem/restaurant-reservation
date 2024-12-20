package com.havrem.restaurantreserverbackend.recommendationengine;

import com.havrem.restaurantreserverbackend.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationEngine {
    public List<Restaurant> determineRecommendations(RecommendationStrategy recommendationStrategy) {
        return recommendationStrategy.execute();
    }
}
