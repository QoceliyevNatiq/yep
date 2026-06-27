package com.ecommerce.yep.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record CartItemRequest(

        @NotNull(message = "choose product for removing!")
        Long productId,

        @Min(value = 1,message = "quantitiy must be at least 1")
        @NotNull(message = "quantity haven't be empty")
        Integer quantity
) {
}
