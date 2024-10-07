package com.learning.springplayground.global.common.apiPayload.code;

import com.learning.springplayground.global.common.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

    default CustomResponse<Void> getErrorResponse() {
        return CustomResponse.onFailure(getCode(), getMessage());
    }
}
