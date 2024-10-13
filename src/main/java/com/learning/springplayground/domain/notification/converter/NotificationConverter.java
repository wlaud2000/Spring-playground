package com.learning.springplayground.domain.notification.converter;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.learning.springplayground.domain.notification.dto.FcmSendDto;
import com.learning.springplayground.domain.notification.entity.Noti;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationConverter {

    // FcmSendDto를 기반으로 Firebase Message 객체를 생성하는 메서드
    public static Message toFirebaseMessage(FcmSendDto fcmSendDto) {
        // 알림 정보 설정
        Notification notification = Notification.builder()
                .setTitle(fcmSendDto.getTitle())
                .setBody(fcmSendDto.getBody())
                .build();

        // 메시지 빌드 (토큰과 알림 내용 포함)
        return Message.builder()
                .setToken(fcmSendDto.getToken())
                .setNotification(notification)
                .build();
    }

    public static Noti toNoti(FcmSendDto fcmSendDto, boolean success) {
        return Noti.builder()
                .title(fcmSendDto.getTitle())
                .body(fcmSendDto.getBody())
                .token(fcmSendDto.getToken())
                .sentAt(LocalDateTime.now())
                .success(success)
                .build();
    }
}
