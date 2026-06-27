package com.ecommerce.yep.service.impl;

import com.ecommerce.yep.dto.AdminOrderResponse;
import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.mapper.OrderMapper;
import com.ecommerce.yep.model.Order;
import com.ecommerce.yep.model.OrderStatus;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.repo.OrderRepo;
import com.ecommerce.yep.service.OrderService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ecommerce.yep.model.OrderStatus.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepository;
    private final OrderMapper orderMapper;



    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(SystemMessage.ORDER_NOT_FOUND));
        return orderMapper.maptoOrderResponse(order);
    }

    @Override
    public Page<OrderResponse> getOrderByUserId(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId,pageable)
                .map(orderMapper::maptoOrderResponse);


    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order =  orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(SystemMessage.ORDER_NOT_FOUND));
        if(order.getStatus() != (OrderStatus.PENDING) ) {
            throw new BaseException(SystemMessage.FAILED);
        }
        order.setStatus(CANCELLED);
        orderRepository.save(order);
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(SystemMessage.ORDER_NOT_FOUND));
        if( !order.getUser().getId().equals( user.getId())) {
            throw new BaseException(SystemMessage.FAILED);
        }
        return orderMapper.maptoOrderResponse(order);
    }

    @Override
    public Page<AdminOrderResponse> getAllOrdersForAdmin(Pageable pageable) {
    return (orderRepository.findAll(pageable).map(orderMapper::maptoAdminOrderResponse));
    }



    @Override
    @Transactional
    public void shipOrder(Long orderId, String trackingCode) {
        Order order =  orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(SystemMessage.ORDER_NOT_FOUND));
        order.setStatus(SHIPPED);
        order.setOrderTrackNumber(trackingCode);
        orderRepository.save(order);

    }

    @Override
    public Page<OrderResponse> getOrdersByStatus(User user, Pageable pageable, OrderStatus status) {
       Page<OrderResponse> responses = orderRepository.findByUserIdAndStatus(user.getId(), status,pageable).map(orderMapper::maptoOrderResponse);
//        orderRepository.findByUserId(user.getId(),pageable)
//                .stream().filter(order -> order.getStatus() == status)

        return responses;
    }
}
