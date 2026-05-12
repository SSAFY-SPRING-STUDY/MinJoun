package org.example.springstudy.domain.post.controller.dto;

import lombok.Getter;
import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.post.entity.PostEntity;

@Getter
public class PostRequest {

    private String title;
    private String content;

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title, content, author);
    }

}
