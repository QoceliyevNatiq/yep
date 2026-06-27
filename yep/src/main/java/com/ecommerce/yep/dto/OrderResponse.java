package com.ecommerce.yep.dto;

import com.ecommerce.yep.model.OrderItem;
import com.ecommerce.yep.model.OrderStatus;
import com.ecommerce.yep.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderResponse(


    String orderTrackNumber,
    Long userId,
    OrderStatus status,
    String shippingAddress,
    BigDecimal totalPrice,
    List<OrderItemResponse> orderItems
) {}
