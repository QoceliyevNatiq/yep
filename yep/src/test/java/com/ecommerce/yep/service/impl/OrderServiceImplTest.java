package com.ecommerce.yep.service.impl;


import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.mapper.OrderMapper;
import com.ecommerce.yep.model.Order;
import com.ecommerce.yep.model.OrderStatus;
import com.ecommerce.yep.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    OrderRepo orderRepository;
    @Mock
    OrderMapper orderMapper;
    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void getOrderById_shouldReturnOrderResponse_WhenOrderIsExists()
    {
        Order order = new Order();
        order.setId(1L);

        OrderResponse orderResponse = OrderResponse.builder().build();

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderMapper.maptoOrderResponse(order)).thenReturn(orderResponse);

        OrderResponse result = orderService.getOrderById(order.getId());
        assertEquals(orderResponse, result);
        verify(orderRepository).findById(order.getId());

    }

    @Test
    void getOrderById_shouldThrowException_WhenOrderIsNotExists()
    {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BaseException.class, ()  -> orderService.getOrderById(99L));
    }

    @Test
    void cancelOrder_shouldSetStatusCancelled_WhenOrderIsCancelled()
    {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.cancelOrder(order.getId());

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        verify(orderRepository).save(order);
        verify(orderRepository).findById(order.getId());
    }

    @Test
    void cancelOrder_shouldThrowException_WhenOrderIsNotExists()
    {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BaseException.class, () -> orderService.cancelOrder(99L));
        verify(orderRepository).findById(99L);
        verify(orderRepository, never()).save(any());
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"PAID", "SHIPPED", "DELIVERED", "CANCELLED"})
    void cancelOrder_shouldThrowException_WhenOrderIsNotPending(OrderStatus status)
    {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(status);
        order.setOrderTrackNumber("1");

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        assertThrows(BaseException.class, () -> orderService.cancelOrder(order.getId()));
        verify(orderRepository, never()).save(any());
        verify(orderRepository).findById(order.getId());
    }

    @Test
    void shippedOrder_shouldSetStatusShipped_WhenOrderIsShipped()
    {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderTrackNumber("1");

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.shipOrder(order.getId(), order.getOrderTrackNumber());

        assertEquals(OrderStatus.SHIPPED, order.getStatus());
        assertEquals("1", order.getOrderTrackNumber());
        verify(orderRepository).save(order);
        verify(orderRepository).findById(order.getId());
    }

    @ParameterizedTest
    @CsvSource({"1, Truck1","2, Truck2", "3, Truck3"})
    void shippedOrder_shouldSetStatusShipped_WhenOrderIsShipped(Long orderId, String trackNumber)
    {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderTrackNumber(trackNumber);

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.shipOrder(orderId, trackNumber);

        assertEquals(OrderStatus.SHIPPED, order.getStatus());
        assertEquals(trackNumber, order.getOrderTrackNumber());
        verify(orderRepository).save(order);
        verify(orderRepository).findById(order.getId());
    }

    @Test
    void shippedOrder_shouldThrowException_WhenOrderIsNotExists()
    {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BaseException.class, () -> orderService.shipOrder(99L,"1"));
        verify(orderRepository).findById(99L);
        verify(orderRepository, never()).save(any());
    }

}
