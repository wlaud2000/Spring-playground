package com.learning.springplayground.domain.notification.exception;

import com.learning.springplayground.global.common.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotificationErrorCode implements BaseErrorCode {

    FCMTOKEN_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "Notification404_0", "해당 유저의 FCM 토큰이 이미 존재합니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
