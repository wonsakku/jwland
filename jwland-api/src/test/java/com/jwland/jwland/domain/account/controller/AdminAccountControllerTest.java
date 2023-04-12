package com.jwland.jwland.domain.account.controller;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.AccountRole;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.RoleType;
import com.jwland.jwland.entity.status.SchoolClassification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class AdminAccountControllerTest {

    @Autowired
    MockMvc mockMvc;



    @Test
    @WithMockUser(username = "te123123st", password = "tessdf3st", roles = {"ADMIN"})
    void test() throws Exception {
        this.mockMvc.perform(get("/admin/accounts?size=10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data.size").value(10))
        ;
    }
}