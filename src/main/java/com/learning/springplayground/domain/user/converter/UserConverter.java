package com.learning.springplayground.domain.user.converter;

import com.learning.springplayground.domain.user.dto.request.UserReqDto;
import com.learning.springplayground.domain.user.dto.response.UserResDto;
import com.learning.springplayground.domain.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserConverter {

    //DTO -> Entity
    public static User toEntity(UserReqDto.SignUpRequestDto requestDto, PasswordEncoder passwordEncoder) {
        //PasswordEncoder를 통해서 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.password());
        return User.builder()
                .email(requestDto.email())
                .password(encodedPassword)
                .nickname(requestDto.nickname())
                .univ(requestDto.univ())
                .major(requestDto.major())
                .isStaff(false) //회원가입 시 유저로 설정
                .build();
    }

    //Entity -> DTO
    public static UserResDto.SignUpResponseDto fromSignUp(User user) {
        return UserResDto.SignUpResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .univ(user.getUniv())
                .major(user.getMajor())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UserResDto.UserResponseDto fromUser(User user) {
        return UserResDto.UserResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .univ(user.getUniv())
                .major(user.getMajor())
                .build();
    }
}
