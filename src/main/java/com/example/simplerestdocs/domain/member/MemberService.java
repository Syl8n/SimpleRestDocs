package com.example.simplerestdocs.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member signup(MemberForm form) {
        return memberRepository.save(Member.builder()
            .email(form.getEmail())
            .password(form.getPassword())
            .build());
    }

    public boolean signin(MemberForm form) {
        Member member = memberRepository.findByEmail(form.getEmail())
            .orElseThrow(() -> new RuntimeException("No matching user"));
        return member.getPassword().equals(form.getPassword());
    }
}
