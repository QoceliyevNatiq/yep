package com.ecommerce.yep.repo;

import com.ecommerce.yep.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {


}
