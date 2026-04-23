package org.example.springstudy.post.entity;

import lombok.Getter;
import org.example.springstudy.post.controller.dto.PostRequest;

@Getter
public class PostEntity {
    private static Long AUTO_INCREMENT = 1L;

    private final Long id;
    private String title;
    private String content;
    private final Long authorId;

    public PostEntity(String title, String content, Long authorId) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public void modify(PostRequest request) {
        this.title = request.title();
        this.content = request.content();
    }
}
