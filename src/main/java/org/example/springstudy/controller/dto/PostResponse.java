package org.example.springstudy.controller.dto;

import lombok.Getter;
import org.example.springstudy.entity.PostEntity;

@Getter
public class PostResponse {
    private final long id;
    private final String title;
    private final String content;
    private final String author;

    public PostResponse(long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostResponse fromEntity(PostEntity entity) {
        return new PostResponse(entity.getId(), entity.getTitle(), entity.getContent(), entity.getAuthor());
    }
}
