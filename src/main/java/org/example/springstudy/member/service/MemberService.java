package org.example.springstudy.member.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.member.controller.dto.MemberRequest;
import org.example.springstudy.member.controller.dto.MemberResponse;
import org.example.springstudy.member.entity.MemberEntity;
import org.example.springstudy.member.repository.MemberRepository;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return MemberResponse.from(entity);
    }
}
