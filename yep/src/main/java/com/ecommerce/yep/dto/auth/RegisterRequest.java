package com.ecommerce.yep.dto.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(


        @NotBlank(message = "Ad boş ola bilməz!")
        String name,

        @NotBlank(message = "Email boş ola bilməz")
        @Email(message = "Email formatı düzgün deyil!")
        String email,

        @Size(min = 6, message = "Şifrə ən azı 6 simvol olmalıdır")
        String password,
        String surname
) {}
