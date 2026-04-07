package org.example.springstudy.entity;


import lombok.Getter;
import org.example.springstudy.controller.dto.PostRequest;
import org.example.springstudy.controller.dto.PostResponse;

@Getter
public class PostEntity {
    private static Long AUTO_INCREMENT = 1L;

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void modify(PostRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.author = request.getAuthor();
    }
}
