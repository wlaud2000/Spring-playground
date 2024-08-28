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
}
