package com.ecommerce.yep.mapper;

import com.ecommerce.yep.dto.AdminOrderResponse;
import com.ecommerce.yep.dto.OrderItemResponse;
import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.model.Order;
import com.ecommerce.yep.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    public AdminOrderResponse response1;

    public  OrderResponse maptoOrderResponse(Order order){
        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemResponse(
                        orderItem.getProduct().getId(),
                        orderItem.getPriceAtPurchase(),
                        orderItem.getQuantity(),
                        orderItem.getProduct().getName()
                ))
                .toList();
        OrderResponse response = OrderResponse.builder()
                .orderTrackNumber(order.getOrderTrackNumber())
                .shippingAddress(order.getShippingAddress())
                .totalPrice(order.getTotalPrice())
                .orderItems(itemResponses)
                .status(order.getStatus())
                .userId(order.getUser().getId())
                .build();

        return response;
    }

    public AdminOrderResponse maptoAdminOrderResponse(Order order){
        List<OrderItemResponse> itemResponse = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemResponse(
                        orderItem.getProduct().getId(),
                        orderItem.getPriceAtPurchase(),
                        orderItem.getQuantity(),
                        orderItem.getProduct().getName()
//                        orderItem.getOrderItemId(),
//                        orderItem.getOrder().getOrderItems().size(),
//                        orderItem.getOrder().getUser().getEmail(),
//                        orderItem.getOrder().getUser().getFullName(),
//                        orderItem.getOrder().getStatus(),
//                        orderItem.getOrder().getOrderTrackNumber(),
//                        orderItem.getOrder().getTotalPrice()
                ))
                .toList();
     return   AdminOrderResponse.builder()
             .id(order.getId())
             .itemCount(itemResponse.size())
             .userEmail(order.getUser().getEmail())
             .fullName(order.getUser().getFullName())
             .status(order.getStatus().name())
             .trackNumber(order.getOrderTrackNumber())
             .totalPrice(order.getTotalPrice().toString())
             .build();
    }


}
