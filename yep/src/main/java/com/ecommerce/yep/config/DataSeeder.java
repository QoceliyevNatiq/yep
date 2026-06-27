package com.ecommerce.yep.config;

import com.ecommerce.yep.model.Role;
import com.ecommerce.yep.model.User;
import com.ecommerce.yep.repo.UserRepo;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;




@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo repo;

    @Value("${app.admin.default-email}")
    private String adminEmail;

    @Value("${app.admin.default-password}")
    private String adminPassword;

    @Value("${app.admin.default-name}")
    private String adminName;

    @Override
    public void run(String... args) throws Exception {

        if(!repo.existsByEmail(adminEmail)){

            User user = User.builder()
                    .role(Role.ADMIN)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .fullName(adminName)
                    .build();
            repo.save(user);
            log.info("Created admin user with email {} and name {}", adminEmail, adminName);
        }
        else log.info("Admin user with email {} and name {} already exists", adminEmail, adminName);



    }
}
