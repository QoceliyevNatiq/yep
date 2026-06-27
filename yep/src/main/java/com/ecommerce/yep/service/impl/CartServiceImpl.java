package com.ecommerce.yep.service.impl;

import com.ecommerce.yep.dto.CartResponse;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.mapper.CartMapper;
import com.ecommerce.yep.model.Cart;
import com.ecommerce.yep.model.CartItem;
import com.ecommerce.yep.model.Product;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.repo.CartRepo;
import com.ecommerce.yep.repo.ProductRepo;
import com.ecommerce.yep.repo.UserRepo;
import com.ecommerce.yep.service.CartService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartMapper mapper;

    @Transactional
    @Override
    public CartResponse addFromCart(User user, Long productId, Integer quantity) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new BaseException(SystemMessage.PRODUCT_NOT_FOUND));
        Cart cart = getOrCreateCart(user);
        Optional<CartItem> founded = cart.getItems().stream().filter(item ->item.getProduct().getId().equals(product.getId())).findFirst();
        if (founded.isPresent()) {
            CartItem item = founded.get();
            item.setQuantity(item.getQuantity() + quantity);

        }
        else{
            CartItem item = CartItem.builder()
                    .quantity(quantity)
                    .product(product)
                    .unitPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                    .cart(cart)
                    .build();
            cart.getItems().add(item);

        }


        return mapper.toResponse(cart);
    }
    @Transactional
    @Override
    public CartResponse removeFromCart(User user, Long productId) {
        Cart cart = getOrCreateCart(user);
        boolean removed = cart.getItems( ).removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));
        if (removed) {
             cartRepo.save(cart);
            return mapper.toResponse(cart);
        }

        return mapper.toResponse(cart);
    }
    @Transactional
    @Override
    public Cart getOrCreateCart(User user) {
        return cartRepo.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder().user(user).build();
                    return cartRepo.save(newCart);
                });


    }

    @Transactional
    @Override
    public CartResponse getOrCreateCartDto(User user) {
        Cart cart = getOrCreateCart(user);
        return mapper.toResponse(cart);

    }

    @Transactional
    @Override
    public CartResponse clearCart(User user) {
        Cart  cart = getOrCreateCart(user);
        List<CartItem> items = cart.getItems();
        items.clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepo.save(cart);
        return mapper.toResponse(cart);


    }


    @Transactional
    @Override
    public CartResponse updateCartItemQuantity(User user, Long itemId, Integer quantity) {

        Cart cart = cartRepo.findByUserId(user.getId())
                .orElseThrow(() -> new BaseException(SystemMessage.CART_NOT_FOUND));

        if (quantity <= 0) {
            removeFromCart(user, itemId);
            return mapper.toResponse(cart);
        }
        cart.updateItemQuantity(itemId, quantity);
        cartRepo.save(cart);
        return mapper.toResponse(cart);

    }



}
