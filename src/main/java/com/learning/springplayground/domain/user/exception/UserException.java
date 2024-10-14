package com.learning.springplayground.domain.user.exception;

import com.learning.springplayground.domain.notification.exception.NotificationErrorCode;
import com.learning.springplayground.global.common.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class UserException extends CustomException {

    public UserException(UserErrorCode errorCode){
        super(errorCode);
    }
}