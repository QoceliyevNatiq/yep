package com.ecommerce.yep.service.impl;


import com.ecommerce.yep.dto.auth.AuthResponse;
import com.ecommerce.yep.dto.auth.LoginRequest;
import com.ecommerce.yep.dto.auth.RegisterRequest;
import com.ecommerce.yep.model.Role;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.repo.UserRepo;
import com.ecommerce.yep.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user =  User.builder()
              .email(request.email())
              .password(passwordEncoder.encode(request.password()))
                .fullName(request.name() + " " + request.surname())
                .role(Role.CUSTOMER)
                        .build();
        userRepo.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new  AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepo.findByEmail(request.email())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken);
    }
}
