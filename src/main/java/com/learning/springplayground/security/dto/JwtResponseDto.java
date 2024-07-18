package com.learning.springplayground.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponseDto {

    private String accessToken;

    private String refreshToken;
}
