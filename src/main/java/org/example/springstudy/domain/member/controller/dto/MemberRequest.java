package org.example.springstudy.domain.member.controller.dto;

import org.example.springstudy.domain.member.entity.MemberEntity;

public record MemberRequest(
    String username,
    String password,
    String nickname
) {
    public static MemberEntity toEntity(MemberRequest request) {
        return MemberEntity.create(request.username(), request.password(), request.nickname());
    }
}
