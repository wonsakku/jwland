package com.jwland.jwland.domain.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.AccountRole;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.RoleType;
import com.jwland.jwland.entity.status.SchoolClassification;
import com.jwland.jwland.jwt.JwtConstants;
import com.jwland.jwland.jwt.TokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void init(){
        School school = new School(SchoolClassification.HIGH, "sampleSchool", "Y");
        em.persist(school);

        final AccountDto accountDto = new AccountDto("testcode", "testcode", "testcode", school.getId(), Grade.TWO.name());
        final String encoded = passwordEncoder.encode(accountDto.getPassword());
        final Account account = accountDto.toInsertEntity(encoded, school);

        em.persist(account);

        final AccountRole accountRole1 = new AccountRole(account, RoleType.ROLE_ADMIN);
        final AccountRole accountRole2 = new AccountRole(account, RoleType.ROLE_USER);

        em.persist(accountRole1);
        em.persist(accountRole2);
    }


    @Test
    @DisplayName("로그인 성공 시 header 에 Authorization 에 jwt 토큰이 있는지, jwt이 유효한지 확인")
    void loginTest() throws Exception{

        final MvcResult mvcResult = this.mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(new LoginDto("testcode", "testcode")))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(JwtConstants.AUTHORIZATION))
                .andReturn();

        final String jwtToken = mvcResult.getResponse()
                .getHeader(JwtConstants.AUTHORIZATION)
                .replace(JwtConstants.BEARER, "");

        assertThat(tokenProvider.validateToken(jwtToken)).isTrue();
    }

}









