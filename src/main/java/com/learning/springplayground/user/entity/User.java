package com.learning.springplayground.user.entity;

import com.learning.springplayground.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String univ;

    private String major;

    private String nickname;

    private String password;

    private boolean isStaff;

    //회원 이름 변경시 서비스단에서 사용되는 메서드
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    //회원 비밀번호 변경시 서비스단에서 사용되는 메서드
    public void updatePassword(String password) {
        this.password = password;
    }

    //회원 대학 변경시 서비스단에서 사용되는 메서드
    public void updateUniv(String univ) {
        this.univ = univ;
    }

    //회원 전공 변경시 서비스단에서 사용되는 메서드
    public void updateMajor(String major) {
        this.major = major;
    }
}
