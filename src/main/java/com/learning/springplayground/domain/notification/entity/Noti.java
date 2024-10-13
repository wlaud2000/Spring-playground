package com.learning.springplayground.domain.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "notification")
public class Noti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;       // 알림 제목
    private String body;        // 알림 내용
    private String token;       // 수신자 FCM 토큰
    private LocalDateTime sentAt; // 알림 발송 시간
    private boolean success;    // 발송 성공 여부
}
