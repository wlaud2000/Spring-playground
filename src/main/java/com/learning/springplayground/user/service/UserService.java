package com.learning.springplayground.user.service;

import com.learning.springplayground.user.dto.request.RegisterRequestDto;
import com.learning.springplayground.user.dto.response.RegisterResponseDto;
import com.learning.springplayground.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
//    @Transactional
//    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
//
//        //이메일 중복 확인
//        if(userRepository.existsByEmail(registerRequestDto.getEmail())) {
//            throw new RuntimeException("해당 이메일이 이미 존재합니다.");
//        }
//
//
//
//    }
}
