package org.example.springstudy.controller.dto;

import org.example.springstudy.entity.PostEntity;

public record PostRequest(
        String title,
        String content,
        String author
) {
    public static PostEntity toEntity(PostRequest request) {
        return new PostEntity(request.title(), request.content(), request.author());
    }
}
