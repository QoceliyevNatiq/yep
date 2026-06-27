package com.ecommerce.yep.exception;


import com.ecommerce.yep.dto.ApiResponse;
import com.ecommerce.yep.util.SystemMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleBaseException(BaseException baseException){

        log.warn("Biznes Xətası: {}",baseException.getSystemMessage().getMessage());
        return ApiResponse.ok(baseException.getSystemMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        log.warn("Validasiya Xətası: {}", errors);
        return ApiResponse.error(SystemMessage.VALIDATION_ERROR, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleException(Exception ex){

        log.error("Gözlənilməz Sistem Xətası: ", ex);
        return ApiResponse.error(SystemMessage.INTERNAL_SERVER_ERROR);
    }


}
