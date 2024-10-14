package com.learning.springplayground.domain.user.exception;

import com.learning.springplayground.global.common.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER400_0", "해당 이메일의 유저 정보가 이미 존재합니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_0", "해당 유저가 존재하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
