package org.example.springstudy.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.auth.component.SessionManager;
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

    @PostMapping
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> me(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = sessionManager.getToken(authHeader);
            Long memberId = sessionManager.getMemberId(token);

            if (memberId == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            else
                return ResponseEntity.ok(memberService.findById(memberId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
