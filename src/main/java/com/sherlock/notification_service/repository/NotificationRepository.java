package com.sherlock.notification_service.repository;

import com.sherlock.notification_service.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
