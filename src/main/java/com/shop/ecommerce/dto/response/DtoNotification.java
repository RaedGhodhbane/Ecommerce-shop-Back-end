package com.shop.ecommerce.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DtoNotification() {
    static String userId;
    static String message;
    static boolean status;
    static LocalDate datePosted;
}
