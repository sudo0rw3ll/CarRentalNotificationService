package com.sherlock.notification_service.service;


import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationTypeCreateDto;
import com.sherlock.notification_service.dto.NotificationTypeDto;
import com.sherlock.notification_service.dto.NotificationTypeUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationTypeService {
    NotificationTypeDto createNotification(NotificationTypeCreateDto notificationTypeCreateDto);
    NotificationTypeDto updateNotification(Long id, NotificationTypeUpdateDto notificationTypeUpdateDto);
    NotificationTypeDto deleteNotification(Long id);

    Page<NotificationTypeDto> findAll(Pageable pagable);
}
