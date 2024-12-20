package com.havrem.restaurantreserverbackend.recommendationengine;

import com.havrem.restaurantreserverbackend.models.Restaurant;

import java.util.List;

public interface RecommendationStrategy {
    List<Restaurant> execute();
}
