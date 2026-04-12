package org.example.springstudy.member.entity;

import lombok.Getter;
import org.example.springstudy.member.controller.dto.MemberRequest;

@Getter
public class MemberEntity {

    private static Long AUTO_INCREMENT = 1L;

    Long id;
    String loginId;
    String password;
    String name;

    public MemberEntity(String loginId, String password, String name) {
        this.id = AUTO_INCREMENT++;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public static MemberEntity toEntity(MemberRequest request) {
        return new MemberEntity(request.loginId(), request.password(), request.name());
    }
}
