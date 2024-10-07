package com.learning.springplayground.global.common.apiPayload.exception;

import com.learning.springplayground.global.common.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final BaseErrorCode code;

    public CustomException(BaseErrorCode errorCode) {
        this.code = errorCode;
    }
}
