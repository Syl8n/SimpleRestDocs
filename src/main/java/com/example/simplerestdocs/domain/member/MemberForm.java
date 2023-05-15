package com.example.simplerestdocs.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberForm {
    private String email;
    private String password;
}
