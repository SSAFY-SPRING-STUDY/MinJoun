package org.example.springstudy.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.controller.dto.PostRequest;
import org.example.springstudy.controller.dto.PostResponse;
import org.example.springstudy.entity.PostEntity;
import org.example.springstudy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());
        PostEntity returnedEntity = postRepository.save(entity);
        return PostResponse.fromEntity(returnedEntity);
    }

    public List<PostResponse> findAll() {
        List<PostEntity> returnedEntity = postRepository.findAll();

        List<PostResponse> responses = new ArrayList<>();
        for (PostEntity entity : returnedEntity) {
            responses.add(PostResponse.fromEntity(entity));
        }
        return responses;
    }

    public PostResponse findById(Long id) {
        PostEntity returnedEntity = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID값에 맞는 게시물이 존재하지 않습니다!"));
        return PostResponse.fromEntity(returnedEntity);
    }

    public void update(Long id, PostRequest request) {
        postRepository.update(id, request);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
