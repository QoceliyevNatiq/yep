package com.ecommerce.yep.model;



import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.util.SystemMessage;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private BigDecimal unitPrice;

    public void updateQuantity(Integer quantitiy){
        if(quantitiy<0){
            throw new BaseException(SystemMessage.VALIDATION_ERROR);
        }
        this.quantity = quantitiy;
        this.totalPrice = this.unitPrice.multiply(BigDecimal.valueOf(quantitiy));
    }
}

