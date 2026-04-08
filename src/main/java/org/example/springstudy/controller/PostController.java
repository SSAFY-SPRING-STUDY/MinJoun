package org.example.springstudy.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.controller.dto.PostRequest;
import org.example.springstudy.controller.dto.PostResponse;
import org.example.springstudy.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 생성
    // 201 : PostResponse
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.save(request));
    }

    // 게시글 전체 조회
    // 200 : PostResponse
    @GetMapping
    public List<PostResponse> findAllPosts() {
        return postService.findAll();
    }

    // id로 게시글 단건 조회
    // 200 : PostResponse
    // 404
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(postService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // id로 기존 게시물 수정
    // 200
    // 404
    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
    }

    // id로 게시물 삭제
    // 200
    // 404
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
