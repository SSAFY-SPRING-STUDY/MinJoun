package org.example.springstudy.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.common.response.ApiResponse;
import org.example.springstudy.member.controller.dto.MemberRequest;
import org.example.springstudy.member.controller.dto.MemberResponse;
import org.example.springstudy.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SessionManager sessionManager;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<MemberResponse> join(@RequestBody MemberRequest request) {
        return ApiResponse.success(memberService.save(request));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public ApiResponse<MemberResponse> me(@RequestHeader("Authorization") String authHeader) {
        String token = sessionManager.getToken(authHeader);
        Long memberId = sessionManager.getMemberId(token);

        return ApiResponse.success(memberService.findById(memberId));
    }
}
