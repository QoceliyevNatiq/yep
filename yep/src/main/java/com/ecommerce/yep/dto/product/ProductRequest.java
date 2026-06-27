package com.ecommerce.yep.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "ad bos olmamalidir!")
        String name,
        String imageUrl,
        String description,

        @NotNull(message = "qiymet bos ola bilmez")
        @Min(value = 0,message = "qiymet menfi ola bilmez")
        BigDecimal price,

        @NotNull(message = "stock null ola bilmez")
        @Min(value = 0,message = "stock menfi ola bilmez")
        Integer stock,

        @NotNull(message = "mutleq bir kategoriyaya aid olmalidir")
        Long categoryId,

        Boolean active
) {
    public ProductRequest{
        if(active == null){
            active = true;
        }
    }
}
