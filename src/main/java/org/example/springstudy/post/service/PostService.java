package org.example.springstudy.post.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.common.exception.CustomException;
import org.example.springstudy.common.exception.ErrorCode;
import org.example.springstudy.post.controller.dto.PostRequest;
import org.example.springstudy.post.controller.dto.PostResponse;
import org.example.springstudy.post.entity.PostEntity;
import org.example.springstudy.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponse save(PostRequest request, Long memberId) {
        return PostResponse.fromEntity(postRepository.save(PostRequest.toEntity(request, memberId)));
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponse::fromEntity)
                .toList();
    }

    public PostResponse findById(Long id) {
        PostEntity returnedEntity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return PostResponse.fromEntity(returnedEntity);
    }

    public void update(Long id, PostRequest request) {
        postRepository.update(id, request);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
