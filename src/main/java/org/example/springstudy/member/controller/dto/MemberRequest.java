package org.example.springstudy.member.controller.dto;

public record MemberRequest(String loginId, String password, String name) {
}
