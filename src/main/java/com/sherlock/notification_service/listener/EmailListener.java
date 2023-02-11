package com.sherlock.notification_service.listener;

import com.sherlock.notification_service.domain.Notification;
import com.sherlock.notification_service.domain.NotificationType;
import com.sherlock.notification_service.dto.*;
import com.sherlock.notification_service.exception.NotFoundException;
import com.sherlock.notification_service.listener.helper.MessageHelper;
import com.sherlock.notification_service.repository.NotificationRepository;
import com.sherlock.notification_service.repository.NotificationTypeRepository;
import com.sherlock.notification_service.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationRepository notificationRepository;

    public EmailListener(MessageHelper messageHelper, EmailService emailService,
                         NotificationTypeRepository notificationTypeRepository,
                         NotificationRepository notificationRepository){
        this.emailService = emailService;
        this.notificationTypeRepository = notificationTypeRepository;
        this.messageHelper = messageHelper;
        this.notificationRepository = notificationRepository;
    }

    @JmsListener(destination="${destination.activate_account}", concurrency = "5-10")
    public void sendActivationMail(Message message) throws JMSException {
        ActivationMailDto activationMailDto = messageHelper.getMessage(message, ActivationMailDto.class);
        NotificationType notificationType = notificationTypeRepository.findById(1L).orElseThrow(() -> new NotFoundException("Nema ga"));
        String notContent = notificationType.getNotification_content();

        notContent = notContent.replace("%ime", activationMailDto.getName());
        notContent = notContent.replace("%prezime", activationMailDto.getSurname());
        notContent = notContent.replace("%link", activationMailDto.getLink());
        System.out.println("stigao sam do ovde");

        emailService.sendSimpleMessage(activationMailDto.getEmail(),"Aktiviraj svoj mejl!",notContent);

        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setEmailToSendTo(activationMailDto.getEmail());
        notification.setContent(notContent);

        notificationRepository.save(notification);
    }

    @JmsListener(destination = "${destination.reset_password}", concurrency = "5-10")
    public void sendPasswordResetMail(Message message) throws JMSException{
        ResetPasswordMailDto resetPasswordMailDto = messageHelper.getMessage(message, ResetPasswordMailDto.class);
        NotificationType notificationType = notificationTypeRepository.findById(2L).orElseThrow(() -> new NotFoundException("Nema ga"));
        String notContent = notificationType.getNotification_content();

        notContent = notContent.replace("%ime", resetPasswordMailDto.getName());
        notContent = notContent.replace("%pass", resetPasswordMailDto.getPassword());

        emailService.sendSimpleMessage(resetPasswordMailDto.getEmail(), "Reset lozinke", notContent);

        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setEmailToSendTo(resetPasswordMailDto.getEmail());
        notification.setContent(notContent);

        notificationRepository.save(notification);
    }

    @JmsListener(destination = "${destination.reservation_create}", concurrency = "5-10")
    public void sendReservationMail(Message message) throws JMSException{
        ReservationMailDto reservationMailDto = messageHelper.getMessage(message, ReservationMailDto.class);
        NotificationType notificationType = notificationTypeRepository.findById(3L).orElseThrow(() -> new NotFoundException("Nema ga"));
        String notContent = notificationType.getNotification_content();

        notContent = notContent.replace("%ime", reservationMailDto.getFirstName());
        notContent = notContent.replace("%prezime", reservationMailDto.getLast_name());
        notContent = notContent.replace("%vozilo", reservationMailDto.getVehicle());
        notContent = notContent.replace("%firma", reservationMailDto.getCompany_name());
        notContent = notContent.replace("%sdatum", reservationMailDto.getStart_date().toString());
        notContent = notContent.replace("%edatum", reservationMailDto.getEnd_date().toString());
        notContent = notContent.replace("%ukupaniznos", reservationMailDto.getTotal_price().toString());
        notContent = notContent.replace("%popust", reservationMailDto.getDiscount().toString());

        emailService.sendSimpleMessage(reservationMailDto.getEmail(), "Rezervacija za korisnika " + reservationMailDto.getFirstName() + " "
         + reservationMailDto.getLast_name(), notContent);

        Notification notification = new Notification();
        notification.setContent(notContent);
        notification.setNotificationType(notificationType);
        notification.setEmailToSendTo(reservationMailDto.getEmail());

        notificationRepository.save(notification);

        NotificationType managerNotType = notificationTypeRepository.findById(4L).orElseThrow(() -> new NotFoundException("nema ga"));
        String mNotContent = managerNotType.getNotification_content();

        mNotContent = mNotContent.replace("%managername", reservationMailDto.getCompany_manager_name());
        mNotContent = mNotContent.replace("%ime", reservationMailDto.getFirstName());
        mNotContent = mNotContent.replace("%prezime", reservationMailDto.getLast_name());
        mNotContent = mNotContent.replace("%vozilo", reservationMailDto.getVehicle());
        mNotContent = mNotContent.replace("%sdate", reservationMailDto.getStart_date().toString());
        mNotContent = mNotContent.replace("%edate", reservationMailDto.getEnd_date().toString());
        mNotContent = mNotContent.replace("%cena", reservationMailDto.getTotal_price().toString());
        mNotContent = mNotContent.replace("%popust", reservationMailDto.getDiscount().toString());

        emailService.sendSimpleMessage(reservationMailDto.getCompany_manager_email(), "Rezervacija za korisnika " + reservationMailDto.getFirstName() + " "
                + reservationMailDto.getLast_name(), mNotContent);

        Notification mNotification = new Notification();
        mNotification.setNotificationType(managerNotType);
        mNotification.setContent(mNotContent);
        mNotification.setEmailToSendTo(reservationMailDto.getCompany_manager_email());

        notificationRepository.save(mNotification);
    }

    @JmsListener(destination = "${destination.cancel_reservation}", concurrency = "5-10")
    public void sendCancelationMail(Message message) throws JMSException{
        ReservationCancelMailDto reservationCancelMailDto = messageHelper.getMessage(message, ReservationCancelMailDto.class);
        NotificationType notificationType = notificationTypeRepository.findById(5L).orElseThrow(() -> new NotFoundException("Nema ga"));
        String notContent = notificationType.getNotification_content();

        notContent = notContent.replace("%ime", reservationCancelMailDto.getUser_name());
        notContent = notContent.replace("%prezime", reservationCancelMailDto.getUser_last_name());
        notContent = notContent.replace("%vozilo", reservationCancelMailDto.getVehicle_name());
        notContent = notContent.replace("%sdate", reservationCancelMailDto.getStart_date().toString());
        notContent = notContent.replace("%edate", reservationCancelMailDto.getEnd_date().toString());

        emailService.sendSimpleMessage(reservationCancelMailDto.getUser_email(), "Otkazivanje rezervacija za korisnika " + reservationCancelMailDto.getUser_name() + " "
                + reservationCancelMailDto.getUser_last_name(), notContent);

        Notification notification = new Notification();
        notification.setContent(notContent);
        notification.setNotificationType(notificationType);
        notification.setEmailToSendTo(reservationCancelMailDto.getUser_email());

        notificationRepository.save(notification);

        NotificationType managerNotType = notificationTypeRepository.findById(6L).orElseThrow(() -> new NotFoundException("nema ga"));
        String mNotContent = managerNotType.getNotification_content();

        mNotContent = mNotContent.replace("%ime", reservationCancelMailDto.getManager_name());
        mNotContent = mNotContent.replace("%kime", reservationCancelMailDto.getUser_name());
        mNotContent = mNotContent.replace("%kprezime", reservationCancelMailDto.getUser_last_name());
        mNotContent = mNotContent.replace("%vozilo", reservationCancelMailDto.getVehicle_name());
        mNotContent = mNotContent.replace("%sdate", reservationCancelMailDto.getStart_date().toString());
        mNotContent = mNotContent.replace("%edate", reservationCancelMailDto.getEnd_date().toString());

        emailService.sendSimpleMessage(reservationCancelMailDto.getManager_email(), "Otkazivanje rezervacije za korisnika " + reservationCancelMailDto.getUser_name() + " "
                + reservationCancelMailDto.getUser_last_name(), mNotContent);

        Notification mNotification = new Notification();
        mNotification.setNotificationType(managerNotType);
        mNotification.setContent(mNotContent);
        mNotification.setEmailToSendTo(reservationCancelMailDto.getManager_email());

        notificationRepository.save(mNotification);
    }

    @JmsListener(destination = "${destination.notify_customer_new}", concurrency = "5-10")
    public void sendReminder(Message message) throws JMSException{
        ReservationReminderDto reservationReminderDto = messageHelper.getMessage(message, ReservationReminderDto.class);

        NotificationType notificationType = notificationTypeRepository.findById(7L).orElseThrow(() -> new NotFoundException("Nema ga"));
        String notificationContent = notificationType.getNotification_content();

        notificationContent = notificationContent.replace("%ime", reservationReminderDto.getCustomerName());
        notificationContent = notificationContent.replace("%vozilo", reservationReminderDto.getVehicleToReserve());
        notificationContent = notificationContent.replace("%days", reservationReminderDto.getDaysLeft().toString());

        emailService.sendSimpleMessage(reservationReminderDto.getCustomerEmail(), "Podsetnik za rezervaciju", notificationContent);

        Notification notification = new Notification();
        notification.setEmailToSendTo(reservationReminderDto.getCustomerEmail());
        notification.setNotificationType(notificationType);
        notification.setContent(notificationContent);

        notificationRepository.save(notification);
    }
}
