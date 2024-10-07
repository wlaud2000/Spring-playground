package com.learning.springplayground.domain.user.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDto {

    @Builder
    public record SignUpResponseDto(
            Long id,
            String email,
            String nickname,
            String univ,
            String major,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record UserResponseDto(
            String email,
            String nickname,
            String univ,
            String major,
            String role
    ) {
    }
}
