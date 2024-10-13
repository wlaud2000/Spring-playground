package com.learning.springplayground.domain.notification.controller;

import com.learning.springplayground.domain.notification.dto.FcmSendDto;
import com.learning.springplayground.domain.notification.dto.FcmTokenRequestDto;
import com.learning.springplayground.domain.notification.service.FcmService;
import com.learning.springplayground.domain.notification.service.FcmTokenService;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.global.common.apiPayload.CustomResponse;
import com.learning.springplayground.global.jwt.annotation.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmController {

    private final FcmTokenService fcmTokenService;
    private final FcmService fcmService;

    // FCM 토큰 저장 API
    @PostMapping("/token")
    public CustomResponse<String> registerFcmToken(@CurrentUser AuthUser authUser,
                                                   @RequestBody FcmTokenRequestDto fcmTokenRequestDto) {
        fcmTokenService.saveFcmToken(authUser.getEmail(), fcmTokenRequestDto.getFcmToken());
        return CustomResponse.onSuccess("성공적으로 FCM 토큰이 저장되었습니다.");
    }

    // FCM 푸시 알림 전송 API
    @PostMapping("/send")
    public CustomResponse<String> sendNotification(@RequestBody FcmSendDto fcmSendDto) {
        fcmService.sendFcmNotification(fcmSendDto);
        return CustomResponse.onSuccess("성공적으로 알림이 전송되었습니다.");
    }
}
