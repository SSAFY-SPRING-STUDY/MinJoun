package org.example.springstudy.auth.controller.dto;

public record LoginRequest(
    String username,
    String password
) {
}
