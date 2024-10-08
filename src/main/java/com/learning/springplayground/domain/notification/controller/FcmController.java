package com.learning.springplayground.domain.notification.controller;

import com.learning.springplayground.domain.notification.dto.FcmSendDto;
import com.learning.springplayground.domain.notification.service.FcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
public class FcmController {

    private final FcmService fcmService;

    @Autowired
    public FcmController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    // FCM 푸시 알림을 전송하는 API 엔드포인트
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody FcmSendDto fcmSendDto) {
        fcmService.sendFcmNotification(fcmSendDto);
        return ResponseEntity.ok("FCM notification sent successfully");
    }
}
