package com.shop.ecommerce.dto.request;

public record NotificationDto(
        String userId,
        String message,
        boolean status
) {
}
