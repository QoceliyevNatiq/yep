package com.ecommerce.yep.dto;

import com.ecommerce.yep.model.Product;

import java.math.BigDecimal;

public record OrderItemResponse(

        Long productId,
        BigDecimal priceAtPurchase,
        Integer quantity,
        String productName
) {
}
