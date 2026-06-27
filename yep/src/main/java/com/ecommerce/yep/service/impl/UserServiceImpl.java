package com.ecommerce.yep.service.impl;

import com.ecommerce.yep.dto.auth.LoginRequest;
import com.ecommerce.yep.dto.auth.RegisterRequest;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.model.Role;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.repo.UserRepo;
import com.ecommerce.yep.service.UserService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
//    @Override
//    public void Register(RegisterRequest request) {
//        if(userRepo.existsByEmail(request.email())){
//
//            throw new BaseException(SystemMessage.EMAIL_ALREADY_EXISTS);
//        }
//        User user =  User.builder()
//              .email(request.email())
//              .password(passwordEncoder.encode(request.password()))
//                .fullName(request.name() + " " + request.surname())
//                .role(Role.CUSTOMER)
//                        .build();
//        userRepo.save(user);
//    }
//
//    @Override
//    public void Login(LoginRequest request) {
//        User foundUser = userRepo.findByEmail(request.email())
//                .orElseThrow(() -> new BaseException(SystemMessage.USER_NOT_FOUND));
//
//        if(!passwordEncoder.matches(request.password(), foundUser.getPassword())){
//            throw new BaseException(SystemMessage.WRONG_PASSWORD);
//        }
//
//    }


}
