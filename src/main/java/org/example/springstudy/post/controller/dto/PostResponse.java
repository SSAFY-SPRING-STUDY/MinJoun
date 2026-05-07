package org.example.springstudy.post.controller.dto;

import org.example.springstudy.post.entity.PostEntity;

public record PostResponse (
    Long id,
    String title,
    String content,
    String author
) {

    public static PostResponse fromEntity(PostEntity postEntity) {
        return new PostResponse(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), postEntity.getAuthor());
    }
}
