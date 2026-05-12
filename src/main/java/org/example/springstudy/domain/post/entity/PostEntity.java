package org.example.springstudy.domain.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springstudy.domain.member.entity.MemberEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private MemberEntity author;

    private PostEntity(String title, String content, MemberEntity author) {
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
