package com.ecommerce.yep.controller;


import com.ecommerce.yep.dto.ApiResponse;
import com.ecommerce.yep.dto.CartItemRequest;
import com.ecommerce.yep.dto.CartResponse;
import com.ecommerce.yep.mapper.CartMapper;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.service.CartService;
import com.ecommerce.yep.util.SystemMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;


    @PostMapping("/add")
    public ApiResponse<CartResponse> addToCart(@AuthenticationPrincipal User user, @Valid @RequestBody CartItemRequest cartItemRequest) {
        CartResponse response = cartService.addFromCart(user,cartItemRequest.productId(), cartItemRequest.quantity());
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }
    @DeleteMapping("/remove/{productId}")
    public ApiResponse<CartResponse> removeFromCart(@AuthenticationPrincipal User user,@Valid @PathVariable Long productId) {
        CartResponse response = cartService.removeFromCart(user, productId);
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }

    @GetMapping("/getcart")
    public ApiResponse<CartResponse> getCart(@AuthenticationPrincipal User user) {
        CartResponse response = cartService.getOrCreateCartDto(user);
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }

    @DeleteMapping("/clear")
    public ApiResponse<CartResponse> clearCart(@AuthenticationPrincipal User user) {
        CartResponse response = cartService.clearCart(user);
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }

    @PutMapping("/update")
    public ApiResponse<CartResponse> updateQuantitiy(@AuthenticationPrincipal User user,@Valid @RequestBody CartItemRequest cartItemRequest) {
        CartResponse response = cartService.updateCartItemQuantity(user, cartItemRequest.productId(), cartItemRequest.quantity());
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }










}
