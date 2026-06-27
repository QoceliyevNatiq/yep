package com.ecommerce.yep.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(nullable = false,updatable = false)
    private BigDecimal priceAtPurchase;

    @Column(nullable = false,updatable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false,updatable = false)
    private Order order;

    public OrderItem(Order order, CartItem item ){
        this.order = order;
        this.product = item.getProduct();
        this.quantity = item.getQuantity();
        this.priceAtPurchase = item.getUnitPrice();
    }
}


