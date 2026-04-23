package org.example.springstudy.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.auth.controller.dto.LoginRequest;
import org.example.springstudy.auth.controller.dto.LoginResponse;
import org.example.springstudy.common.exception.CustomException;
import org.example.springstudy.common.exception.ErrorCode;
import org.example.springstudy.member.entity.MemberEntity;
import org.example.springstudy.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if (!member.getPassword().equals(request.password())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        String accessToken = sessionManager.createSession(member.getId());
        return new LoginResponse(accessToken, "Bearer");
    }

    public void logout(String token) {
        sessionManager.removeSession(token);
    }
}
