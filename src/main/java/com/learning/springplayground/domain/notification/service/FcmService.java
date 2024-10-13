package com.learning.springplayground.domain.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.learning.springplayground.domain.notification.converter.NotificationConverter;
import com.learning.springplayground.domain.notification.dto.FcmSendDto;
import com.learning.springplayground.domain.notification.entity.Noti;
import com.learning.springplayground.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmService {

    private final NotificationRepository notificationRepository;

    // FCM 푸시 알림 전송 메서드
    public void sendFcmNotification(FcmSendDto fcmSendDto) {
        Message message = NotificationConverter.toFirebaseMessage(fcmSendDto);

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("푸시 알림 전송 성공: {}", response);

            // 알림 전송 성공 시 DB에 저장
            saveNotification(fcmSendDto, true);

        } catch (Exception e) {
            log.error("푸시 알림 전송 실패", e);

            // 알림 전송 실패 시 DB에 저장
            saveNotification(fcmSendDto, false);
        }
    }

    // 알림을 DB에 저장하는 메서드
    public void saveNotification(FcmSendDto fcmSendDto, boolean success) {
        Noti notification = NotificationConverter.toNoti(fcmSendDto, success);
        notificationRepository.save(notification);
    }
}
