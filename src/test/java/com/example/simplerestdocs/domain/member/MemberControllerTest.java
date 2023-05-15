package com.example.simplerestdocs.domain.member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @MockBean
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    public void success_signup() throws Exception {
        Member member = Member.builder().email("abc@gmail.com").password("1234").build();
        MemberForm form = new MemberForm();
        form.setEmail("email");
        form.setPassword("1111");
        // given
        given(memberService.signup(any()))
            .willReturn(member);

        // when
        ResultActions result = mockMvc.perform(post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)));

        // then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.email").value(member.getEmail()),
                jsonPath("$.password").value(member.getPassword())
            )
            .andDo(document("member-signup",
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("password").description("비밀번호")
                ),
                responseFields(
                    fieldWithPath("id").description("회원 id"),
                    fieldWithPath("email").description("회원 이메일"),
                    fieldWithPath("password").description("회원 비밀번호")
                )));
    }
}