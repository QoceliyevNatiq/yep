package com.ecommerce.yep.service;

import com.ecommerce.yep.dto.CartResponse;
import com.ecommerce.yep.model.Cart;
import com.ecommerce.yep.model.CartItem;
import com.ecommerce.yep.model.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartResponse addFromCart(User user, Long productId, Integer quantity);
    CartResponse removeFromCart(User user, Long productId);
    Cart getOrCreateCart(User user);
    CartResponse getOrCreateCartDto(User user);

    CartResponse clearCart(User user);
    CartResponse updateCartItemQuantity(User user, Long itemId, Integer quantity);
}
