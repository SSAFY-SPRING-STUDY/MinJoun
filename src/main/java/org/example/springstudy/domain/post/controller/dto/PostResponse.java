package org.example.springstudy.domain.post.controller.dto;

import org.example.springstudy.domain.member.controller.dto.MemberResponse;
import org.example.springstudy.domain.post.entity.PostEntity;

public record PostResponse (
    Long id,
    String title,
    String content,
    MemberResponse memberResponse
) {

    public static PostResponse fromEntity(PostEntity postEntity) {
        return new PostResponse(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), MemberResponse.from(postEntity.getAuthor()));
    }
}
