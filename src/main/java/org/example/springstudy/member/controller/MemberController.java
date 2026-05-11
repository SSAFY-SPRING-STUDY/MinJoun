package org.example.springstudy.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
import org.example.springstudy.auth.util.AuthTokenUtils;
import org.example.springstudy.member.controller.dto.MemberRequest;
import org.example.springstudy.member.controller.dto.MemberResponse;
import org.example.springstudy.member.service.MemberService;
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
    public MemberResponse join(@RequestBody MemberRequest request) {
        return memberService.join(request);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long memberId = sessionManager.getMemberId(sessionKey);

        return memberService.getMemberInfo(memberId);
    }
}
