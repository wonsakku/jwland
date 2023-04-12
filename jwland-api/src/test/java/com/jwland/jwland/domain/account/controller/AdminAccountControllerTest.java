package com.jwland.jwland.domain.account.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class AdminAccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "test")
    void test() throws Exception{
        this.mockMvc.perform(get("/admin/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").value("OK"))
        ;


    }
}