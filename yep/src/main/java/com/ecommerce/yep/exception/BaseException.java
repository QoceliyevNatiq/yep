package com.ecommerce.yep.exception;

import com.ecommerce.yep.util.SystemMessage;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final SystemMessage systemMessage;

    public BaseException(SystemMessage systemMessage) {
        super(String.valueOf(systemMessage));
        this.systemMessage = systemMessage;
    }
}
