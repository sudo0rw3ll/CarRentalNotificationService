package com.sherlock.notification_service.mapper;

import com.sherlock.notification_service.domain.Notification;
import com.sherlock.notification_service.domain.NotificationType;
import com.sherlock.notification_service.dto.NotificationCreateDto;
import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationUpdateDto;
import com.sherlock.notification_service.exception.NotFoundException;
import com.sherlock.notification_service.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    private NotificationTypeMapper notificationTypeMapper;
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationMapper(NotificationTypeMapper notificationTypeMapper,
                              NotificationTypeRepository notificationTypeRepository){
        this.notificationTypeMapper = notificationTypeMapper;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public NotificationDto notificationToNotificationDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setEmail(notification.getEmailToSendTo());
        notificationDto.setNotificationTypeDto(notificationTypeMapper.notificationTypeToNotificationTDto(notification.getNotificationType()));

        return notificationDto;
    }

    public Notification notificationDtoToNotification(NotificationDto notificationDto){
        Notification notification = new Notification();
        notification.setEmailToSendTo(notificationDto.getEmail());
        notification.setId(notification.getId());
        notification.setNotificationType(notificationTypeMapper.notificationTypeDtoToNotificationType(notificationDto.getNotificationTypeDto()));

        return notification;
    }

    public Notification notificationCreateDtoToNotification(NotificationCreateDto notificationCreateDto){
        Notification notification = new Notification();
        notification.setEmailToSendTo(notificationCreateDto.getEmailToSendTo());
        NotificationType notificationType = notificationTypeRepository
                .findById(notificationCreateDto.getType_id())
                .orElseThrow(() -> new NotFoundException(String.format("Notification type with id %id has not been found", notificationCreateDto.getType_id())));

        notification.setNotificationType(notificationType);

        return notification;
    }
}
