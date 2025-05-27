package com.shop.ecommerce.services;

import com.shop.ecommerce.dto.request.NotificationDto;
import com.shop.ecommerce.dto.response.DtoNotification;
import com.shop.ecommerce.entities.Notification;
import com.shop.ecommerce.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;

    @Transactional
    @Override
    public String addNotification(NotificationDto notificationDto) {
        if (notificationDto != null && notificationDto.message() != null ) {
            Notification notification = Notification.builder().datePosted(LocalDate.now())
                    .message(notificationDto.message()).userId(notificationDto.userId())
                    .status(notificationDto.status()).build();
            notificationRepository.save(notification);
            return "Notification has been successfully saved.";
        } else {
            return "Fields are null.";
        }
    }

    @Override
    public List<DtoNotification> getNotificationsByIdOrGeneral(String id) {
        return notificationRepository.findByIdAndByAll(id).orElse(Collections.emptyList())
                .stream()
                .map(n -> DtoNotification.builder()
                        .userId(n.getUserId())
                        .datePosted(n.getDatePosted())
                        .message(n.getMessage())
                        .status(n.isStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
