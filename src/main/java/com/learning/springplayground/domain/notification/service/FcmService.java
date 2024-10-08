package com.learning.springplayground.domain.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.learning.springplayground.domain.notification.converter.FcmMessageConverter;
import com.learning.springplayground.domain.notification.dto.FcmSendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FcmService {

    // FCM 푸시 알림을 전송하는 메서드
    public void sendFcmNotification(FcmSendDto fcmSendDto) {
        // FcmSendDto를 Firebase Message 객체로 변환
        Message message = FcmMessageConverter.convertToFirebaseMessage(fcmSendDto);

        // FCM 서버에 메시지 전송
        sendFcmMessageToFirebase(message);
    }

    // FCM 서버에 실제로 메시지를 전송하는 메서드
    private void sendFcmMessageToFirebase(Message message) {
        try {
            // 메시지 전송
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully sent message: {}", response);

        } catch (Exception e) {
            // 예외 발생 시 에러 로그 기록
            log.error("Failed to send FCM notification", e);
            throw new RuntimeException("Failed to send FCM notification", e);
        }
    }
}
