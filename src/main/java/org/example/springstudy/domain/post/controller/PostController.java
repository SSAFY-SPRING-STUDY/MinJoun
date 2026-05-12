package org.example.springstudy.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.component.SessionManager;
import org.example.springstudy.domain.auth.util.AuthTokenUtils;
import org.example.springstudy.domain.post.controller.dto.PostRequest;
import org.example.springstudy.domain.post.controller.dto.PostResponse;
import org.example.springstudy.domain.post.service.PostService;
import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
import org.example.springstudy.global.response.ApiResponse;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> createPost(@RequestHeader("Authorization") String bearerToken,
                                                @RequestBody PostRequest request) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long authorId = sessionManager.getMemberId(sessionKey);

        return ApiResponse.success("게시물을 생성하였습니다.", postService.create(request, authorId));
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        return ApiResponse.success("게시물을 조회하였습니다.", postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPostById(@PathVariable Long postId) {
        return ApiResponse.success("게시물을 조회하였습니다.", postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(@RequestHeader("Authorization") String bearerToken,
                                                @PathVariable Long postId,
                                                @RequestBody PostRequest request) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long authorId = sessionManager.getMemberId(sessionKey);

        return ApiResponse.success("게시물이 수정되었습니다.", postService.update(request, postId, authorId));

    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deletePost(@RequestHeader("Authorization") String bearerToken,
                                        @PathVariable Long postId) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long authorId = sessionManager.getMemberId(sessionKey);

        postService.deleteById(postId, authorId);

        return ApiResponse.success("게시물이 삭제되었습니다.");
    }
}
