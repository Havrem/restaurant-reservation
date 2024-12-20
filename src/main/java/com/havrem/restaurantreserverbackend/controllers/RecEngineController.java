package com.havrem.restaurantreserverbackend.controllers;


import com.havrem.restaurantreserverbackend.models.Restaurant;
import com.havrem.restaurantreserverbackend.recommendationengine.Keyword;
import com.havrem.restaurantreserverbackend.services.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecEngineController {
    private final RecommendationService recommendationService;

    public RecEngineController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendation-engine/{keyword}")
    public List<Restaurant> getRecommendations(@PathVariable Keyword keyword) {
        return recommendationService.generateRecommendations(keyword);
    }
}
