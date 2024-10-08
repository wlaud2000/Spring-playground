package com.learning.springplayground.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FCMConfig {

    // application.yml에서 Firebase 서비스 계정 파일 경로를 가져옴
    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        // ClassPathResource로 리소스 경로에서 파일을 불러옴
        ClassPathResource resource = new ClassPathResource(firebaseConfigPath);

        // Firebase 옵션 설정
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                .build();

        // FirebaseApp을 초기화하고 빈으로 등록
        return FirebaseApp.initializeApp(options);
    }
}
