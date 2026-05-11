package org.example.springstudy.member.controller.dto;

import org.example.springstudy.member.entity.MemberEntity;

public record MemberRequest(
    String username,
    String password,
    String nickname
) {
    public static MemberEntity toEntity(MemberRequest request) {
        return MemberEntity.create(request.username(), request.password(), request.nickname());
    }
}
