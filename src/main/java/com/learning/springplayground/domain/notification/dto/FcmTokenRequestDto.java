package com.learning.springplayground.domain.notification.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FcmTokenRequestDto {

    private String fcmToken;
}
