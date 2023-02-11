package com.sherlock.notification_service.service.impl;

import com.sherlock.notification_service.domain.Notification;
import com.sherlock.notification_service.domain.NotificationType;
import com.sherlock.notification_service.dto.NotificationCreateDto;
import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationUpdateDto;
import com.sherlock.notification_service.exception.NotFoundException;
import com.sherlock.notification_service.mapper.NotificationMapper;
import com.sherlock.notification_service.mapper.NotificationTypeMapper;
import com.sherlock.notification_service.repository.NotificationRepository;
import com.sherlock.notification_service.repository.NotificationTypeRepository;
import com.sherlock.notification_service.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationTypeMapper notificationTypeMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper,
                                   NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper){
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public NotificationDto createNotification(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationCreateDtoToNotification(notificationCreateDto);
        notificationRepository.save(notification);

        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationUpdateDto notificationUpdateDto) {
        Notification notification = notificationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Notification with id %d cannot be found", id)));

        notification.setEmailToSendTo(notificationUpdateDto.getEmail());

        NotificationType notificationType = notificationTypeRepository
                .findById(notificationUpdateDto.getType_id())
                .orElseThrow(() -> new NotFoundException(String.format("Notification type with id %id has not been found", notificationUpdateDto.getType_id())));

        notification.setNotificationType(notificationType);
        notification.setId(notificationUpdateDto.getType_id());

        notificationRepository.save(notification);

        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public NotificationDto deleteNotification(Long id) {
        Notification notification = notificationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Notification with id %d cannot be found", id)));

        notificationRepository.delete(notification);

        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pagable) {
        return notificationRepository.findAll(pagable)
                .map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public List<NotificationDto> findByType(Long type_id) {
        return notificationRepository.findAll()
                .stream()
                .filter(not -> not.getNotificationType().getId().equals(type_id))
                .map(notificationMapper::notificationToNotificationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> findByEmail(String email) {
        return notificationRepository.findAll()
                .stream()
                .filter(not -> not.getEmailToSendTo().equals(email))
                .map(notificationMapper::notificationToNotificationDto)
                .collect(Collectors.toList());
    }
}
