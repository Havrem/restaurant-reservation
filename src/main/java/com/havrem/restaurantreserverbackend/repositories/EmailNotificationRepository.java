package com.havrem.restaurantreserverbackend.repositories;

import com.havrem.restaurantreserverbackend.models.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
}
