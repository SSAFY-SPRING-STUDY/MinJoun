package org.example.springstudy.controller.dto;

import org.example.springstudy.entity.PostEntity;

public record PostResponse(
        long id,
        String title,
        String content,
        String author
) {
    public static PostResponse fromEntity(PostEntity entity) {
        return new PostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor()
        );
    }
}
