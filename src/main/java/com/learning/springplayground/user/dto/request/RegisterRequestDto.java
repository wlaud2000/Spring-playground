package com.learning.springplayground.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    private String email;

    private String nickname;

    private String password;

    private String univ;

    private String major;

}
