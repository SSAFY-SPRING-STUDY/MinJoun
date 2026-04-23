package org.example.springstudy.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.common.response.ApiResponse;
import org.example.springstudy.post.controller.dto.PostRequest;
import org.example.springstudy.post.controller.dto.PostResponse;
import org.example.springstudy.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final SessionManager sessionManager;

    // 게시글 생성
    // 201 : PostResponse
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<PostResponse> createPost(@RequestHeader("Authorization") String authHeader, @RequestBody PostRequest request) {
        String token = sessionManager.getToken(authHeader);
        Long memberId = sessionManager.getMemberId(token);
        return ApiResponse.success(postService.save(request, memberId));
    }

    // 게시글 전체 조회
    // 200 : PostResponse
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiResponse<List<PostResponse>> findAllPosts() {
        return ApiResponse.success(postService.findAll());
    }

    // id로 게시글 단건 조회
    // 200 : PostResponse
    // 404
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ApiResponse<PostResponse> findPostById(@PathVariable Long id) {
        return ApiResponse.success(postService.findById(id));
    }

    // id로 기존 게시물 수정
    // 200
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ApiResponse<Void> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
        return ApiResponse.success();
    }

    // id로 게시물 삭제
    // 200
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.success();
    }
}
