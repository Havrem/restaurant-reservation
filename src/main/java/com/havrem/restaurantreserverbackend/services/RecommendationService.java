package com.havrem.restaurantreserverbackend.services;

import com.havrem.restaurantreserverbackend.models.Restaurant;
import com.havrem.restaurantreserverbackend.recommendationengine.Keyword;
import com.havrem.restaurantreserverbackend.recommendationengine.RecommendationEngine;
import com.havrem.restaurantreserverbackend.recommendationengine.RecommendationFactory;
import com.havrem.restaurantreserverbackend.recommendationengine.RecommendationStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    private final RecommendationEngine recommendationEngine;
    private final RecommendationFactory recommendationFactory;

    public RecommendationService(RecommendationEngine recommendationEngine, RecommendationFactory recommendationFactory) {
        this.recommendationEngine = recommendationEngine;
        this.recommendationFactory = recommendationFactory;
    }

    public List<Restaurant> generateRecommendations(Keyword keyword) {
        RecommendationStrategy recommendationStrategy = recommendationFactory.createRecommendationStrategy(keyword);
        return recommendationEngine.determineRecommendations(recommendationStrategy);
    }
}
