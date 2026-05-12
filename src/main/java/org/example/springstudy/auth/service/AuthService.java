package org.example.springstudy.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.auth.controller.dto.LoginRequest;
import org.example.springstudy.auth.controller.dto.LoginResponse;
import org.example.springstudy.member.entity.MemberEntity;
import org.example.springstudy.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public LoginResponse login(LoginRequest request) {
        MemberEntity entity = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!entity.checkPassword(request.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return LoginResponse.from(sessionManager.createSession(entity.getId()));
    }

    public void logout(String sessionKey) {
        sessionManager.removeSession(sessionKey);
    }
}
