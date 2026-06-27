package com.ecommerce.yep.repo;

import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.model.Order;
import com.ecommerce.yep.model.OrderStatus;
import com.ecommerce.yep.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {


    List<Order> user(User user);

    Page<Order> findByUserId(Long userId, Pageable pageable);
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
    Page<Order> findByUserIdAndStatus(Long userId, OrderStatus status, Pageable pageable);
}
