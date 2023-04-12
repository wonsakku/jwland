package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountsDto;
import com.jwland.jwland.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class AdminAccountServiceTest {

    @Autowired
    AdminAccountService accountService;

    @Autowired
    EntityManager em;

    @Test
    void getAccountPaginationTest(){
        final Pageable page = PageRequest.of(0, 20);
    }


}