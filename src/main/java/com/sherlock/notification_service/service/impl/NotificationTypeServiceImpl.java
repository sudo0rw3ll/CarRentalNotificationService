package com.sherlock.notification_service.service.impl;

import com.sherlock.notification_service.domain.Notification;
import com.sherlock.notification_service.domain.NotificationType;
import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationTypeCreateDto;
import com.sherlock.notification_service.dto.NotificationTypeDto;
import com.sherlock.notification_service.dto.NotificationTypeUpdateDto;
import com.sherlock.notification_service.exception.NotFoundException;
import com.sherlock.notification_service.mapper.NotificationTypeMapper;
import com.sherlock.notification_service.repository.NotificationTypeRepository;
import com.sherlock.notification_service.service.NotificationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private NotificationTypeMapper notificationTypeMapper;
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeServiceImpl(NotificationTypeMapper notificationTypeMapper, NotificationTypeRepository notificationTypeRepository){
        this.notificationTypeMapper = notificationTypeMapper;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public NotificationTypeDto createNotification(NotificationTypeCreateDto notificationTypeCreateDto) {
        NotificationType notificationT = notificationTypeMapper.notificationTypeCreateDtoToNotificationType(notificationTypeCreateDto);
        notificationTypeRepository.save(notificationT);

        return notificationTypeMapper.notificationTypeToNotificationTDto(notificationT);
    }

    @Override
    public NotificationTypeDto updateNotification(Long id, NotificationTypeUpdateDto notificationTypeUpdateDto) {
        NotificationType notificationT = notificationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("NotificationType with id %d cannot be found", id)));

        notificationT.setType_name(notificationTypeUpdateDto.getTypeName());
        notificationT.setNotification_content(notificationTypeUpdateDto.getNotificationContent());

        notificationTypeRepository.save(notificationT);

        return notificationTypeMapper.notificationTypeToNotificationTDto(notificationT);
    }

    @Override
    public NotificationTypeDto deleteNotification(Long id) {
        NotificationType notificationT = notificationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("NotificationType with id %d cannot be found", id)));

        notificationTypeRepository.delete(notificationT);

        return notificationTypeMapper.notificationTypeToNotificationTDto(notificationT);
    }

    @Override
    public Page<NotificationTypeDto> findAll(Pageable pagable) {
        return (Page<NotificationTypeDto>) notificationTypeRepository.findAll(pagable)
                .map(notificationTypeMapper::notificationTypeToNotificationTDto);
    }
}
