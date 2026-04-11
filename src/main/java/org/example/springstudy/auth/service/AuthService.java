package org.example.springstudy.auth.service;

import lombok.RequiredArgsConstructor;
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
        MemberEntity member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new RuntimeException("로그인 실패!"));

        if (!member.getPassword().equals(request.password())) {
            throw new RuntimeException("로그인 실패!");
        }

        String accessToken = sessionManager.createSession(member.getId());
        return new LoginResponse(accessToken, "Bearer");
    }

    public void logout(String token) {
        sessionManager.removeSession(token);
    }
}
