package com.learning.springplayground.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserReqDto {

    public record ChangeNickNameRequestDto (
            @Size(min = 1, max = 12, message = "[ERROR] 닉네임은 1자 이상, 12글자 이하여야 합니다.")
            @NotBlank(message = "[ERROR] 닉네임은 필수 입력값입니다.")
            String newNickName
    ) {
    }

    public record LoginRequestDto(
            @NotBlank(message = "[ERROR] 이메일 입력은 필수입니다.")
            @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "[ERROR] 이메일 형식에 맞지 않습니다.")
            String email,

            @Size(min = 8, max = 64, message = "[ERROR] 비밀번호는 8자 이상, 64자 이하여야 합니다.")
            @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).{8,64}$", message = "[ERROR] 알파벳과 숫자가 모두 들어가는 8자리 이상의 비밀번호를 입력해주세요.")
            @NotBlank(message = "[ERROR] 비밀번호는 필수 입력값입니다.")
            String password
    ){
    }

    public record SignUpRequestDto(
            @NotBlank(message = "[ERROR] 이메일 입력은 필수입니다.")
            @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "[ERROR] 이메일 형식에 맞지 않습니다.")
            String email,

            @Size(min = 1, max = 12, message = "[ERROR] 닉네임은 1자 이상, 12글자 이하여야 합니다.")
            @NotBlank(message = "[ERROR] 닉네임은 필수 입력값입니다.")
            String nickname,

            @Size(min = 8, max = 64, message = "[ERROR] 비밀번호는 8자 이상, 64자 이하여야 합니다.")
            @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).{8,64}$", message = "[ERROR] 알파벳과 숫자가 모두 들어가는 8자리 이상의 비밀번호를 입력해주세요.")
            @NotBlank(message = "[ERROR] 비밀번호는 필수 입력값입니다.")
            String password,

            @NotBlank(message = "[ERROR] 대학 입력은 필수입니다.")
            String univ,

            @NotBlank(message = "[ERROR] 전공 입력은 필수입니다.")
            String major
    ){
    }
}
