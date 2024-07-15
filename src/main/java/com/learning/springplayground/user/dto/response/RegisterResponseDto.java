package com.learning.springplayground.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {

    private Long id;

    private String email;

    private String nickname;

    private String univ;

    private String major;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
