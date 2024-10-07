package com.learning.springplayground.domain.user.service;

import com.learning.springplayground.domain.user.converter.UserConverter;
import com.learning.springplayground.domain.user.dto.request.UserReqDto;
import com.learning.springplayground.domain.user.dto.response.UserResDto;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.domain.user.entity.User;
import com.learning.springplayground.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; //password 암호화

    //회원 가입
    @Transactional
    public UserResDto.SignUpResponseDto signUp(UserReqDto.SignUpRequestDto signUpRequestDto) {

        //이메일 중복 확인
        if(userRepository.existsByEmail(signUpRequestDto.email())) {
            throw new RuntimeException("해당 이메일이 이미 존재합니다.");
        }

        //파라미터로 받은 registerRequestDto를 Entity로 변환
        User user = UserConverter.toEntity(signUpRequestDto,passwordEncoder);

        //변환한 Entity를 DB에 저장
        userRepository.save(user);

        //DB에 저장한 Entity를 DTO로 변환 후 Controller단에 반환
        return UserConverter.fromSignUp(user);
    }

    //유저 삭제
    public void deleteUser(AuthUser authUser) {
        User user = userRepository.findByEmail(authUser.getEmail())
                .orElseThrow(()-> new NoSuchElementException("가입된 사용자 정보가 없습니다."));

        //user 삭제
        userRepository.delete(user);

        log.info("[User Service] 사용자가 성공적으로 삭제되었습니다.");
    }

}
