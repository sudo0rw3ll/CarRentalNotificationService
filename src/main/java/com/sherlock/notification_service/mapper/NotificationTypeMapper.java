package com.sherlock.notification_service.mapper;

import com.sherlock.notification_service.domain.Notification;
import com.sherlock.notification_service.domain.NotificationType;
import com.sherlock.notification_service.dto.NotificationCreateDto;
import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationTypeCreateDto;
import com.sherlock.notification_service.dto.NotificationTypeDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {

    public NotificationTypeDto notificationTypeToNotificationTDto(NotificationType notificationType){
        NotificationTypeDto notificationDto = new NotificationTypeDto();
        notificationDto.setId(notificationType.getId());
        notificationDto.setType_name(notificationType.getType_name());
        notificationDto.setNotification_content(notificationType.getNotification_content());

        return notificationDto;
    }

    public NotificationType notificationTypeDtoToNotificationType(NotificationTypeDto notificationTypeDto){
        NotificationType notificationType = new NotificationType();
        notificationType.setType_name(notificationTypeDto.getType_name());
        notificationType.setId(notificationTypeDto.getId());
        notificationType.setNotification_content(notificationTypeDto.getNotification_content());

        return notificationType;
    }

    public NotificationType notificationTypeCreateDtoToNotificationType(NotificationTypeCreateDto notificationTypeCreateDto){
        NotificationType notificationType = new NotificationType();
        notificationType.setType_name(notificationTypeCreateDto.getType_name());
        notificationType.setNotification_content(notificationTypeCreateDto.getNotification_content());

        return notificationType;
    }
}
