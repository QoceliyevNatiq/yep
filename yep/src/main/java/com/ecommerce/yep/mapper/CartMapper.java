package com.ecommerce.yep.mapper;


import com.ecommerce.yep.dto.CartItemResponse;
import com.ecommerce.yep.dto.CartResponse;
import com.ecommerce.yep.model.Cart;
import com.ecommerce.yep.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public CartResponse toResponse(Cart cart) {
        if (cart == null) {
            return null;
        }
        List<CartItemResponse> cartItemDto = cart.getItems().stream()
                .map(this::toDto)
                .toList();


        return(new CartResponse(
                cartItemDto,
                cart.getTotalPrice(),
                cartItemDto.size(),
                cart.getId()

        ));

    }
    private CartItemResponse toDto(CartItem cartItem) {
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .price(cartItem.getTotalPrice())
                .quantity(cartItem.getQuantity())
                .stock(cartItem.getQuantity())
                .description(cartItem.getProduct().getDescription())
                .imageUrl(cartItem.getProduct().getImageUrl())
                .totalPrice(cartItem.getTotalPrice())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .build();
    }
}
