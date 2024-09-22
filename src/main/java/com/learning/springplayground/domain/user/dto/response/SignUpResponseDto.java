package com.learning.springplayground.domain.user.dto.response;

import com.learning.springplayground.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponseDto {

    private Long id;

    private String email;

    private String nickname;

    private String univ;

    private String major;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //Entity를 DTO로 변환 (정적 팩토리 메서드)
    public static SignUpResponseDto from(User user) {
        return SignUpResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .univ(user.getUniv())
                .major(user.getMajor())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
