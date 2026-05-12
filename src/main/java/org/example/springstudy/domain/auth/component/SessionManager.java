package org.example.springstudy.domain.auth.component;

import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long memberId) {
        String sessionKey = UUID.randomUUID().toString();
        sessionStore.put(sessionKey, memberId);

        return sessionKey;
    }

    public void removeSession(String sessionKey) {
        sessionStore.remove(sessionKey);
    }

    public Long getMemberId(String sessionKey) {
        Long memberId = sessionStore.get(sessionKey);
        if (memberId == null) throw new CustomException(ErrorCode.UNAUTHORIZED);
        return memberId;
    }
}
