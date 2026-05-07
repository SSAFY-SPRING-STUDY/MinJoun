package org.example.springstudy.post.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.post.controller.dto.PostRequest;
import org.example.springstudy.post.controller.dto.PostResponse;
import org.example.springstudy.post.entity.PostEntity;
import org.example.springstudy.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());
        return PostResponse.fromEntity(postRepository.save(entity));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::fromEntity)
                .toList();
    }

    public PostResponse findById(Long postId) {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 ID에 맞는 게시글이 없습니다..."));

        return PostResponse.fromEntity(entity);
    }

    public PostResponse update(PostRequest request, Long postId) {
        PostEntity requestEntity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());

        PostEntity entity = postRepository.updateById(requestEntity, postId)
                .orElseThrow(() -> new RuntimeException("해당 ID에 맞는 게시글이 없습니다..."));

        return PostResponse.fromEntity(entity);
    }

    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }
}
