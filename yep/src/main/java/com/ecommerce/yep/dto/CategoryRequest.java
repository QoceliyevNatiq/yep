package com.ecommerce.yep.dto;


public record CategoryRequest(

        Long id,
        String name,
        String description,
        String imageUrl
) {
    }
