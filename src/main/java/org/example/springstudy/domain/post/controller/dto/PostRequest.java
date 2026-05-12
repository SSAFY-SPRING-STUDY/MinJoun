package org.example.springstudy.domain.post.controller.dto;

import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.post.entity.PostEntity;

public record PostRequest(
        String title,
        String content
) {
    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title, content, author);
    }
}
