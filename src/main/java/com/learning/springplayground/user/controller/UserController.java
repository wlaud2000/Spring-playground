package com.learning.springplayground.user.controller;

import com.learning.springplayground.global.annotation.CurrentUser;
import com.learning.springplayground.user.dto.request.SignUpRequestDto;
import com.learning.springplayground.user.dto.response.SignUpResponseDto;
import com.learning.springplayground.user.dto.response.UserResponseDto;
import com.learning.springplayground.user.entity.AuthUser;
import com.learning.springplayground.user.service.UserService;
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

    //회원 가입
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = userService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponseDto);
    }

    //회원 정보 조회
    @GetMapping("")
    public ResponseEntity<UserResponseDto> getUser(@CurrentUser AuthUser authUser) {
        UserResponseDto userResponseDto = userService.getUserByEmail(authUser);
        return ResponseEntity.ok(userResponseDto);
    }

    //회원 탈퇴
    @DeleteMapping("")
    public ResponseEntity<Map<String, String>> deleteUser(@CurrentUser AuthUser authUser) {
        userService.deleteUser(authUser);
        return ResponseEntity.ok(Map.of("message", "회원 탈퇴가 완료되었습니다."));
    }
}
