package com.havrem.restaurantreserverbackend.repositories;

import com.havrem.restaurantreserverbackend.other.Preference;
import com.havrem.restaurantreserverbackend.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByPreference(Preference p);
}
