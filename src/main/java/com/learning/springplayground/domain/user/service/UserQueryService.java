package com.learning.springplayground.domain.user.service;

import com.learning.springplayground.domain.user.converter.UserConverter;
import com.learning.springplayground.domain.user.dto.response.UserResDto;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.domain.user.entity.User;
import com.learning.springplayground.domain.user.exception.UserErrorCode;
import com.learning.springplayground.domain.user.exception.UserException;
import com.learning.springplayground.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;

    //회원 정보 조회
    public UserResDto.UserResponseDto getUserByEmail(AuthUser authUser) {
        User user = userRepository.findByEmail(authUser.getEmail())
                .orElseThrow(()-> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.fromUser(user);
    }

}
