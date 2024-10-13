package com.learning.springplayground.domain.notification.exception;

import com.learning.springplayground.global.common.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class NotificationException extends CustomException {

    public NotificationException(NotificationErrorCode errorCode){
        super(errorCode);
    }
}
