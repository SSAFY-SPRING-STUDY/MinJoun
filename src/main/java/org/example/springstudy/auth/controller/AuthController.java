package org.example.springstudy.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.controller.dto.LoginRequest;
import org.example.springstudy.auth.controller.dto.LoginResponse;
import org.example.springstudy.auth.service.AuthService;
import org.example.springstudy.auth.util.AuthTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        authService.logout(sessionKey);
    }
}
