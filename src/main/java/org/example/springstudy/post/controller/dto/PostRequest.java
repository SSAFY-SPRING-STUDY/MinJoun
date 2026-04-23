package org.example.springstudy.post.controller.dto;

import org.example.springstudy.post.entity.PostEntity;

public record PostRequest(
        String title,
        String content
) {
    public static PostEntity toEntity(PostRequest request, Long authorId) {
        return new PostEntity(request.title(), request.content(), authorId);
    }
}
