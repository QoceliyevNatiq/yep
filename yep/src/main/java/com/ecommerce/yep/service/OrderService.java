package com.ecommerce.yep.service;

import com.ecommerce.yep.dto.AdminOrderResponse;
import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.model.Order;
import com.ecommerce.yep.model.OrderStatus;
import com.ecommerce.yep.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderResponse getOrderById(Long orderId);
    Page<OrderResponse> getOrderByUserId(Long userId, Pageable pageable);
    void cancelOrder(Long orderId);
    OrderResponse getOrderDetails(Long orderId, User user);
    Page<AdminOrderResponse> getAllOrdersForAdmin(Pageable pageable);
    void shipOrder(Long orderId, String trackingCode);
    Page<OrderResponse> getOrdersByStatus(User user, Pageable pageable, OrderStatus status);




}
