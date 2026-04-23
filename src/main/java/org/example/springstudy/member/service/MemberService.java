package org.example.springstudy.member.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.common.exception.CustomException;
import org.example.springstudy.common.exception.ErrorCode;
import org.example.springstudy.member.controller.dto.MemberRequest;
import org.example.springstudy.member.controller.dto.MemberResponse;
import org.example.springstudy.member.entity.MemberEntity;
import org.example.springstudy.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse save(MemberRequest request) {
        MemberEntity entity = memberRepository.save(MemberEntity.toEntity(request));
        return MemberResponse.fromEntity(entity);
    }

    public MemberResponse findById(Long id) {
        MemberEntity entity = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.fromEntity(entity);
    }
}
