package org.example.springstudy.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.controller.dto.LoginRequest;
import org.example.springstudy.domain.auth.controller.dto.LoginResponse;
import org.example.springstudy.domain.auth.service.AuthService;
import org.example.springstudy.domain.auth.util.AuthTokenUtils;
import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
import org.example.springstudy.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.success("로그인에 성공했습니다.", authService.login(request));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> logout(@RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        authService.logout(sessionKey);

        return ApiResponse.success("로그아웃 했습니다.");
    }
}
