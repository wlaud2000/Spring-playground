package com.learning.springplayground.security.userDetails;

import com.learning.springplayground.user.entity.AuthUser;
import com.learning.springplayground.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends AuthUser implements UserDetails {


    //인증용 객체 생성자
    public CustomUserDetails(User user) {
        super(user.getId(), user.getEmail(), user.getPassword(), user.isStaff());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(); //스프링 시큐리티와의 호환성을 위해 ArrayList 사용
        if(super.isStaff()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() { //이메일을 사용자 이름으로 사용하는 경우, getUsername 메서드에서 이메일을 반환하도록 해야함.
        return super.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
