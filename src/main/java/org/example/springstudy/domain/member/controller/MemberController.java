package org.example.springstudy.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.component.SessionManager;
import org.example.springstudy.domain.auth.util.AuthTokenUtils;
import org.example.springstudy.domain.member.controller.dto.MemberRequest;
import org.example.springstudy.domain.member.controller.dto.MemberResponse;
import org.example.springstudy.domain.member.service.MemberService;
import org.example.springstudy.global.exception.CustomException;
import org.example.springstudy.global.exception.error.ErrorCode;
import org.example.springstudy.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final SessionManager sessionManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponse> join(@RequestBody MemberRequest request) {
        return ApiResponse.success("회원가입에 성공했습니다.", memberService.join(request));
    }

    @GetMapping("/me")
    public ApiResponse<MemberResponse> getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long memberId = sessionManager.getMemberId(sessionKey);

        return ApiResponse.success("사용자 조회에 성공했습니다.", memberService.getMemberInfo(memberId));
    }
}
