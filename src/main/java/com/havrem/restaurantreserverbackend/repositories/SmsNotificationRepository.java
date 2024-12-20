package com.havrem.restaurantreserverbackend.repositories;

import com.havrem.restaurantreserverbackend.models.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Long> {
}
