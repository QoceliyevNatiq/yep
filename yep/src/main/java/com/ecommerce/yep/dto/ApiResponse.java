package com.ecommerce.yep.dto;

import com.ecommerce.yep.util.SystemMessage;

import java.time.LocalDateTime;


public record ApiResponse<T>(
        int status,
        String message,
        T data,
        LocalDateTime time

) {

    public static <T> ApiResponse<T> ok(SystemMessage message, T data) {

        return new ApiResponse<>(
                message.getCode(),
                message.getMessage(),
                data,
                LocalDateTime.now()
        );
    }

    public static <T> ApiResponse<T> ok(SystemMessage message) {

        return new ApiResponse<>(
                message.getCode(),
                message.getMessage(),
                null,
                LocalDateTime.now()
        );
    }

    public static <T> ApiResponse<T> error(SystemMessage message, T data) {

        return new ApiResponse<>(
                message.getCode(),
                message.getMessage(),
                data,
                LocalDateTime.now()
        );
    }

    public static <T> ApiResponse<T> error(SystemMessage message) {

        return new ApiResponse<>(
                message.getCode(),
                message.getMessage(),
                null,
                LocalDateTime.now()
        );
    }
}
