package com.learning.springplayground.domain.notification.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmSendDto {
    private String token;   // 클라이언트의 FCM 토큰
    private String title;   // 알림 제목
    private String body;    // 알림 내용

    @Builder(toBuilder = true)
    public FcmSendDto(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }
}
