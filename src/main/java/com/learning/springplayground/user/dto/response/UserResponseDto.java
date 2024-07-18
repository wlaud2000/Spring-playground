package com.learning.springplayground.user.dto.response;

import com.learning.springplayground.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private String email;

    private String nickname;

    private String univ;

    private String major;

    private String role;

    //Entity를 DTO로 변환 (정적 팩토리 메서드)
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .univ(user.getUniv())
                .major(user.getMajor())
                .role(user.isStaff() ? "admin" : "user") //isStaff가 참이면 admin, 참이 아니면 user
                .build();
    }
}
