package org.example.springstudy.post.repository;

import org.example.springstudy.post.controller.dto.PostRequest;
import org.example.springstudy.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository{
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity entity) {
        postList.add(entity);
        return entity;
    }

    public List<PostEntity> findAll() {
        return postList;
    }

    public Optional<PostEntity> findById(Long id) {
        for (PostEntity entity : postList) {
            if (entity.getId().equals(id))
                return Optional.of(entity);
        }
        return Optional.empty();
    }

    public void update(Long id, PostRequest request) {
        findById(id).ifPresent(postEntity -> postEntity.modify(request));
    }

    public void deleteById(Long id) {
        findById(id).ifPresent(postList::remove);
    }
}
