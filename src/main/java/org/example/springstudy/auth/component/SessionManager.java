package org.example.springstudy.auth.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long memberId) {
        String token = UUID.randomUUID().toString();
        sessionStore.put(token, memberId);
        return token;
    }

    public Long getMemberId(String token) {
        return sessionStore.get(token);
    }

    public void removeSession(String token) {
        sessionStore.remove(token);
    }

    public String getToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("유효하지않은 AuthHeader 값 입니다.");
        }
        return authHeader.substring(7);
    }
}