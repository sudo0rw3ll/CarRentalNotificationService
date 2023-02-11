package com.sherlock.notification_service.repository;

import com.sherlock.notification_service.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
}
