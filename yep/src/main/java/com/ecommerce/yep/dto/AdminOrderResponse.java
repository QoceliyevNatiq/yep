package com.ecommerce.yep.dto;

import lombok.Builder;

@Builder
public record AdminOrderResponse(
        Long id,
        int itemCount,
        String userEmail,
        String fullName,
        String internalNote,
        String status,
        String trackNumber,
        String totalPrice
) {
}
