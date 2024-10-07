package com.learning.springplayground.domain.user.controller;

import com.learning.springplayground.domain.user.dto.request.UserReqDto;
import com.learning.springplayground.domain.user.dto.response.UserResDto;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.domain.user.service.UserQueryService;
import com.learning.springplayground.domain.user.service.UserService;
import com.learning.springplayground.global.common.apiPayload.CustomResponse;
import com.learning.springplayground.global.jwt.annotation.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;

    //회원 가입
    @PostMapping(value = "/signup")
    public ResponseEntity<CustomResponse<UserResDto.SignUpResponseDto>> signUp(@Valid @RequestBody UserReqDto.SignUpRequestDto signUpRequestDto) {
        UserResDto.SignUpResponseDto signUpResponseDto = userService.signUp(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(signUpResponseDto));
    }

    //회원 정보 조회
    @GetMapping("")
    public CustomResponse<UserResDto.UserResponseDto> getUser(@CurrentUser AuthUser authUser) {
        UserResDto.UserResponseDto userResponseDto = userQueryService.getUserByEmail(authUser);
        return CustomResponse.onSuccess(userResponseDto);
    }

    //회원 탈퇴
    @DeleteMapping("")
    public CustomResponse<String> deleteUser(@CurrentUser AuthUser authUser) {
        userService.deleteUser(authUser);
        return CustomResponse.onSuccess("회원 탈퇴가 완료되었습니다.");
    }
}
