package com.havrem.restaurantreserverbackend.recommendationengine;

import com.havrem.restaurantreserverbackend.recommendationengine.strategies.Gem;
import com.havrem.restaurantreserverbackend.recommendationengine.strategies.Popular;
import com.havrem.restaurantreserverbackend.recommendationengine.strategies.Rising;
import org.springframework.stereotype.Component;

@Component
public class RecommendationFactory {
    private final Popular popular;
    private final Rising rising;
    private final Gem gem;

    public RecommendationFactory(Popular popular, Rising rising, Gem gem) {
        this.popular = popular;
        this.rising = rising;
        this.gem = gem;
    }

    public RecommendationStrategy createRecommendationStrategy(Keyword keyword) {
        return switch (keyword) {
            case Keyword.POPULAR -> popular;
            case Keyword.RISING -> rising;
            case Keyword.GEM -> gem;
        };
    }
}
