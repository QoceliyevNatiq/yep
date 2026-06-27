package com.ecommerce.yep.dto;

import com.ecommerce.yep.model.Cart;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartItemResponse(
        Long id,
        BigDecimal totalPrice,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal price,
        Long productId,
        String productName,
        String description,
        Integer stock,
        String imageUrl




) {
}
