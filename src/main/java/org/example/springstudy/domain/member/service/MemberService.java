package org.example.springstudy.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.member.controller.dto.MemberRequest;
import org.example.springstudy.domain.member.controller.dto.MemberResponse;
import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.member.repository.MemberRepository;
import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse join(MemberRequest request) {
        return MemberResponse.from(memberRepository.save(MemberEntity.create(request.username(), request.password(), request.nickname())));
    }

    public MemberResponse getMemberInfo(Long memberId) {
        MemberEntity entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberResponse.from(entity);
    }
}
