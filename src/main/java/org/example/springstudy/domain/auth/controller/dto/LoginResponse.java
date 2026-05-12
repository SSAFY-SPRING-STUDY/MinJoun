package org.example.springstudy.domain.auth.controller.dto;

public record LoginResponse(
    String accessToken,
    String tokenType
) {
    private final static String TOKEN_TYPE = "bearer ";

    public static LoginResponse from(String accessToken) {
        return new LoginResponse(accessToken, TOKEN_TYPE);
    }
}
