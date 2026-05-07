package org.example.springstudy.post.repository;

import org.example.springstudy.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity postEntity) {
        postList.add(postEntity);
        return postEntity;
    }

    public Optional<PostEntity> findById(Long postId) {
        for (PostEntity entity : postList) {
            if (entity.getId().equals(postId)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public List<PostEntity> findAll() {
        return postList;
    }

    public Optional<PostEntity> updateById(PostEntity postEntity, Long postId) {
        for (PostEntity entity : postList) {
            if (entity.getId().equals(postId)) {
                entity.update(postEntity.getTitle(), postEntity.getContent());

                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public void deleteById(Long postId) {
        findById(postId).ifPresent(postList::remove);
    }
}
