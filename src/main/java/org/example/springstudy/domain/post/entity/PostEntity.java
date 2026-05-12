package org.example.springstudy.domain.post.entity;

import lombok.Getter;
import org.example.springstudy.domain.member.entity.MemberEntity;

@Getter
public class PostEntity {
    private static long AUTO_INCREMENT_ID = 1;

    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    private PostEntity(String title, String content, MemberEntity author) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(title, content, author);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean isAuthor(MemberEntity author) {
        return this.author.getId().equals(author.getId());
    }
}
