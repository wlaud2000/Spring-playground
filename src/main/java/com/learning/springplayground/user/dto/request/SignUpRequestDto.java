package com.learning.springplayground.user.dto.request;

import com.learning.springplayground.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "[ERROR] 이메일 입력은 필수입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "[ERROR] 이메일 형식에 맞지 않습니다.")
    private String email;

    @Size(min = 1, max = 12, message = "[ERROR] 닉네임은 1자 이상, 12글자 이하여야 합니다.")
    @NotBlank(message = "[ERROR] 닉네임은 필수 입력값입니다.")
    private String nickname;

    @Size(min = 8, max = 64, message = "[ERROR] 비밀번호는 8자 이상, 64자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).{8,64}$", message = "[ERROR] 알파벳과 숫자가 모두 들어가는 8자리 이상의 비밀번호를 입력해주세요.")
    @NotBlank(message = "[ERROR] 비밀번호는 필수 입력값입니다.")
    private String password;

    @NotBlank(message = "[ERROR] 대학 입력은 필수입니다.")
    private String univ;

    @NotBlank(message = "[ERROR] 전공 입력은 필수입니다.")
    private String major;

    //Entity 변환 메서드
    public User toEntity(PasswordEncoder passwordEncoder) {
        //PasswordEncoder를 통해서 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .univ(univ)
                .major(major)
                .isStaff(false) //회원가입 시 유저로 설정
                .build();
    }
}
