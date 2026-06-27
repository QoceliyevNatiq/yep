package com.ecommerce.yep.dto.auth;

public record LoginRequest(

        String email,
        String password

) {
}
