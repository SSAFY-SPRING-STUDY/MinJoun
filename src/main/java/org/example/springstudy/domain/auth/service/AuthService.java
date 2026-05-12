package org.example.springstudy.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.component.SessionManager;
import org.example.springstudy.domain.auth.controller.dto.LoginRequest;
import org.example.springstudy.domain.auth.controller.dto.LoginResponse;
import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.member.repository.MemberRepository;
import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
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
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if (!entity.checkPassword(request.password())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return LoginResponse.from(sessionManager.createSession(entity.getId()));
    }

    public void logout(String sessionKey) {
        sessionManager.removeSession(sessionKey);
    }
}
