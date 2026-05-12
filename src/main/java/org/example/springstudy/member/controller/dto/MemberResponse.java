package org.example.springstudy.member.controller.dto;

import org.example.springstudy.member.entity.MemberEntity;

public record MemberResponse(
    Long id,
    String username,
    String nickname
) {
    public static MemberResponse from(MemberEntity member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getNickname());
    }
}
