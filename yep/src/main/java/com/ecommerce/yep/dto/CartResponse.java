package com.ecommerce.yep.dto;

import com.ecommerce.yep.model.CartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record CartResponse(

        List<CartItemResponse> items,
        BigDecimal totalPrice,
        Integer totalItems,
        Long id
) {
}
