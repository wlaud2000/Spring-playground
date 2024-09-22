package com.learning.springplayground.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {

    private final Long userId;

    private final String email;

    @JsonIgnore
    private final String password;

    private final boolean isStaff;

}
