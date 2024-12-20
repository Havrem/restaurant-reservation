package com.havrem.restaurantreserverbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestaurantReserverBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantReserverBackendApplication.class, args);
	}
}
