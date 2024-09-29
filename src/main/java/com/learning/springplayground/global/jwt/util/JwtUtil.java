package com.learning.springplayground.global.jwt.util;


import com.learning.springplayground.global.jwt.userDetails.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey secretKey; //JWT 서명에 사용되는 비밀 키
    private final Long accessExpMs; //액세스 토큰의 만료 시간
    private final Long refreshExpMs; //리프레시 토큰의 만료 시간

    public JwtUtil(@Value("${spring.jwt.secret}") String secret,
                   @Value("${spring.jwt.token.access-expiration-time}") Long access,
                   @Value("${spring.jwt.token.refresh-expiration-time}") Long refresh) {

        //주어진 시크릿 키 문자열을 바이트 배열로 변환하고, 이를 사용하여 SecretKey 객체 생성
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        accessExpMs = access; // 액세스 토큰 만료 시간 설정
        refreshExpMs = refresh; // 리프레시 토큰 만료 시간 설정
    }

    //JWT 토큰을 입력으로 받아 토큰의 subject 로부터 사용자 Email 추출하는 메서드
    public String getEmail(String token) throws SignatureException {
        log.info("[ JwtUtil ] 토큰에서 이메일을 추출합니다.");
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); //claims의 Subject에서 사용자의 email 추출 (Subject): 토큰의 주체 (일반적으로 사용자 ID나 이메일)
    }

    //JWT 토큰을 입력으로 받아 토큰의 claim 에서 사용자 권한을 추출하는 메서드
    public String getRoles(String token) throws SignatureException {
        log.info("[ JwtUtil ] 토큰에서 권한을 추출합니다.");
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class); //페이로드에서 "role" 클레임을 추출하고 String 타입으로 반환
    }

    //토큰을 발급하는 메서드
    public String tokenProvider(CustomUserDetails userDetails, Instant expirationTime) {

        log.info("[ JwtUtil ] 토큰을 새로 생성합니다.");

        //현재 시간
        Instant issuedAt = Instant.now();

        //토큰에 부여할 권한
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); //스트림의 모든 요소를 하나의 문자열로 결합

        return Jwts.builder()
                .header() //헤더 부분
                .add("typ", "JWT") //JWT 타입을 추가
                .and()
                .subject(userDetails.getUsername()) //Subject 에 username (email) 추가
                .claim("role", authorities) //권한 정보를 클레임에 추가
                .issuedAt(Date.from(issuedAt)) //발행 시간(현재 시간)을 추가
                .expiration(Date.from(expirationTime)) //만료 시간을 추가
                .signWith(secretKey) //서명 정보를 추가
                .compact(); //합치기
    }

    //JWT 액세스 토큰을 생성
    public String createJwtAccessToken(CustomUserDetails customUserDetails) {
        Instant expiration = Instant.now().plusMillis(accessExpMs);
        return tokenProvider(customUserDetails, expiration);
    }

    // principalDetails 객체에 대해 새로운 JWT 리프레시 토큰을 생성
    public String createJwtRefreshToken(CustomUserDetails customUserDetails) {
        Instant expiration = Instant.now().plusMillis(refreshExpMs);
        String refreshToken = tokenProvider(customUserDetails, expiration);
        return refreshToken;
        // 추후 Redis에 refresh 토큰 저장

    }

    // HTTP 요청의 'Authorization' 헤더에서 JWT 액세스 토큰을 검색
    public String resolveAccessToken(HttpServletRequest request) {
        log.info("[ JwtUtil ] 헤더에서 토큰을 추출합니다.");
        String tokenFromHeader = request.getHeader("Authorization");

        if (tokenFromHeader == null || !tokenFromHeader.startsWith("Bearer ")) {
            log.warn("[ JwtUtil ] Request Header 에 토큰이 존재하지 않습니다.");
            return null;
        }

        log.info("[ JwtUtil ] 헤더에 토큰이 존재합니다.");

        return tokenFromHeader.split(" ")[1]; //Bearer 와 분리
    }

    //토큰의 유효성 검사
    public void validateToken(String token) {
        log.info("[ JwtUtil ] 토큰의 유효성을 검증합니다.");
        try {
            // 구문 분석 시스템의 시계가 JWT를 생성한 시스템의 시계 오차 고려
            // 약 3분 허용.
            long seconds = 3 *60;
            boolean isExpired = Jwts
                    .parser()
                    .clockSkewSeconds(seconds)
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
            if (isExpired) {
                log.info("만료된 JWT 토큰입니다.");
            }

        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            //원하는 Exception throw
            throw new SecurityException("잘못된 토큰입니다.");
        } catch (ExpiredJwtException e) {
            //원하는 Exception throw
            throw new ExpiredJwtException(null, null, "만료된 JWT 토큰입니다.");
        }
    }
}
