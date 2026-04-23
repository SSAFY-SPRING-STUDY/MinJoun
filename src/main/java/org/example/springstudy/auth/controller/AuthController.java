package org.example.springstudy.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.controller.dto.LoginRequest;
import org.example.springstudy.auth.controller.dto.LoginResponse;
import org.example.springstudy.auth.service.AuthService;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final SessionManager sessionManager;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = sessionManager.getToken(authHeader);
        authService.logout(token);
        return ApiResponse.success();
    }
}
