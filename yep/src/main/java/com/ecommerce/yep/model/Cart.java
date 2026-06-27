package com.ecommerce.yep.model;




import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.util.SystemMessage;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private  List<CartItem> items = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;

    @PrePersist
    @PreUpdate
    public void syncTotal(){
        this.recalculateTotalPrice();
    }

    public void updateItemQuantity(Long productId, Integer quantity) {
        CartItem carItem = this.items.stream().filter(item -> item.getId().equals(productId)).findFirst().orElseThrow(() ->new BaseException(SystemMessage.PRODUCT_NOT_FOUND));
        carItem.updateQuantity(quantity);
        recalculateTotalPrice();
    }

    public void recalculateTotalPrice() {
       this.totalPrice = this.getItems().stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
