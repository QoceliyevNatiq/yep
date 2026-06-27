package com.ecommerce.yep.controller;


import com.ecommerce.yep.dto.AdminOrderResponse;
import com.ecommerce.yep.dto.ApiResponse;
import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.service.OrderService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderContoller {

    private final OrderService orderService;

    @GetMapping("/getOrder")
    public ApiResponse<OrderResponse> getOrder(@AuthenticationPrincipal User user,@RequestParam Long orderId) {
        OrderResponse response = orderService.getOrderById(orderId);
        return ApiResponse.ok(SystemMessage.SUCCESS, response);
    }

    @GetMapping("/orders")
    public ApiResponse<Page<AdminOrderResponse>> getAllOrders(@AuthenticationPrincipal User user, @PageableDefault(size = 10, sort = "id")Pageable pageable ) {
        Page<AdminOrderResponse> responses = orderService.getAllOrdersForAdmin(pageable);
        return ApiResponse.ok(SystemMessage.SUCCESS, responses);
    }

    @PutMapping("/ship")
    public ApiResponse<OrderResponse> shipOrder(@AuthenticationPrincipal User user,@RequestParam Long orderId) {
        OrderResponse response = orderService.getOrderById(orderId);
        return ApiResponse.ok(SystemMessage.SUCCESS, response);
    }
}
