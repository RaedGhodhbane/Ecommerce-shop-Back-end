package com.shop.ecommerce.services;

import com.shop.ecommerce.dto.request.NotificationDto;
import com.shop.ecommerce.dto.response.DtoNotification;

import java.util.List;

public interface NotificationService {
    String addNotification(NotificationDto notificationDto);

    List<DtoNotification> getNotificationsByIdOrGeneral(String id);
}
