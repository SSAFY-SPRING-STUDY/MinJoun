package org.example.springstudy.post.controller.dto;

import org.example.springstudy.post.entity.PostEntity;

public record PostRequest(
        String title,
        String content,
        String author
) {
    public static PostEntity toEntity(PostRequest request) {
        return new PostEntity(request.title(), request.content(), request.author());
    }
}
