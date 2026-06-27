package com.ecommerce.yep.controller;

import com.ecommerce.yep.dto.AdminOrderResponse;
import com.ecommerce.yep.dto.OrderResponse;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.security.JwtService;
import com.ecommerce.yep.service.OrderService;
import com.ecommerce.yep.util.SystemMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderContoller.class)
@WithMockUser
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    OrderService orderService;

    @MockitoBean
    JwtService jwtService;

    @MockitoBean
    UserDetailsService userDetailsService;

    @Test
    void getOrder_shouldReturn200() throws Exception {
        OrderResponse response = OrderResponse.builder()
                .orderTrackNumber("TRK-123")
                .userId(1L)
                .totalPrice(new BigDecimal("99.99"))
                .build();

        when(orderService.getOrderById(eq(1L))).thenReturn(response);

        mockMvc.perform(get("/api/v1/order/getOrder")
                        .param("orderId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.orderTrackNumber").value("TRK-123"));
    }

    @Test
    void getOrder_shouldReturn400_whenNotFound() throws Exception {
        when(orderService.getOrderById(eq(99L)))
                .thenThrow(new BaseException(SystemMessage.ORDER_NOT_FOUND));

        mockMvc.perform(get("/api/v1/order/getOrder")
                        .param("orderId", "99"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllOrders_shouldReturn200() throws Exception {
        Page<AdminOrderResponse> page = Page.empty();

        when(orderService.getAllOrdersForAdmin(any())).thenReturn(page);

        mockMvc.perform(get("/api/v1/order/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void shipOrder_shouldReturn200() throws Exception {
        OrderResponse response = OrderResponse.builder()
                .orderTrackNumber("TRK-999")
                .userId(1L)
                .totalPrice(BigDecimal.TEN)
                .build();

        when(orderService.getOrderById(eq(1L))).thenReturn(response);

        mockMvc.perform(put("/api/v1/order/ship")
                        .with(csrf())
                        .param("orderId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrder_shouldReturn401_whenUnauthenticated() throws Exception {
        mockMvc.perform(get("/api/v1/order/getOrder")
                        .param("orderId", "1"))
                .andExpect(status().isUnauthorized());
    }
}
