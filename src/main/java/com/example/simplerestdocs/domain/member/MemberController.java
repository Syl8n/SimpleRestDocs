package com.example.simplerestdocs.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody MemberForm form){
        return ResponseEntity.ok(memberService.signup(form));
    }

    @PostMapping("/signin")
    public ResponseEntity<Boolean> signin(@RequestBody MemberForm form){
        return ResponseEntity.ok(memberService.signin(form));
    }
}
