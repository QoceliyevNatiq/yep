package com.ecommerce.yep.controller;

import com.ecommerce.yep.dto.ApiResponse;
import com.ecommerce.yep.dto.auth.AuthResponse;
import com.ecommerce.yep.dto.auth.LoginRequest;
import com.ecommerce.yep.dto.auth.RegisterRequest;
import com.ecommerce.yep.service.UserService;
import com.ecommerce.yep.service.impl.AuthService;
import com.ecommerce.yep.util.SystemMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        AuthResponse response =  authService.register(request);
        return ApiResponse.ok(SystemMessage.SUCCES_REGISTER,response);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.authenticate(request);
        return ApiResponse.ok(SystemMessage.SUCCES_LOGIN,response);
    }
}
