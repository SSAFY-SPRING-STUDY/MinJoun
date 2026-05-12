package org.example.springstudy.domain.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.member.repository.MemberRepository;
import org.example.springstudy.domain.post.controller.dto.PostRequest;
import org.example.springstudy.domain.post.controller.dto.PostResponse;
import org.example.springstudy.domain.post.entity.PostEntity;
import org.example.springstudy.domain.post.repository.PostRepository;
import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity memberEntity = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity entity = request.toEntity(memberEntity);
        return PostResponse.fromEntity(postRepository.save(entity));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::fromEntity)
                .toList();
    }

    public PostResponse getPostById(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return PostResponse.fromEntity(entity);
    }

    @Transactional
    public PostResponse update(PostRequest request, Long postId, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.isAuthor(author)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        post.update(request.title(), request.content());

        return PostResponse.fromEntity(post);
    }

    public void deleteById(Long postId, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.isAuthor(author)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        postRepository.deleteById(postId);
    }
}
