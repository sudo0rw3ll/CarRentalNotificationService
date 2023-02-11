package com.sherlock.notification_service.service;

import com.sherlock.notification_service.dto.NotificationCreateDto;
import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    NotificationDto createNotification(NotificationCreateDto notificationCreateDto);
    NotificationDto updateNotification(Long id, NotificationUpdateDto notificationUpdateDto);
    NotificationDto deleteNotification(Long id);

    Page<NotificationDto> findAll(Pageable pagable);

    List<NotificationDto> findByType(Long type_id);
    List<NotificationDto> findByEmail(String email);
}
