package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.dto.AccountStatusUpdateDto;
import com.jwland.jwland.domain.account.dto.AccountsDto;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.entity.status.AccountStatus;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Arrays;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void getAccountPaginationTest(){
        final Pageable page = PageRequest.of(0, 20);
    }

    @Test
    void updateAccountStatusTest(){
        final School school = new School(SchoolClassification.HIGH, "테스트 학교", "Y");
        em.persist(school);
        final AccountDto accountDto1 = new AccountDto("t1", "t1", "t1", school.getId(), Grade.ONE.name());
        final AccountDto accountDto2 = new AccountDto("t2", "t2", "t2", school.getId(), Grade.ONE.name());

        final Account account1 = accountDto1.toInsertEntity(passwordEncoder.encode(accountDto1.getPassword()), school);
        final Account account2 = accountDto2.toInsertEntity(passwordEncoder.encode(accountDto2.getPassword()), school);

        em.persist(account1);
        em.persist(account2);

        em.flush();
        em.clear();

        final AccountStatusUpdateDto accountStatusUpdateDto = new AccountStatusUpdateDto(Arrays.asList(account1.getId(), account2.getId()), AccountStatus.APPROVED.name());
        accountService.updateAccountStatus(accountStatusUpdateDto);

        // test code에 transaction 설정이 있어서 service까지 transaction이 전파되는 듯.
        // em.flush, em.clear 를 넣어야 update 쿼리가 보임.
        em.flush();
        em.clear();

        final Account findAccount1 = em.find(Account.class, account1.getId());
        final Account findAccount2 = em.find(Account.class, account2.getId());

        assertThat(account1.getAccountStatus()).isEqualTo(AccountStatus.APPROVAL_REQUEST);
        assertThat(account2.getAccountStatus()).isEqualTo(AccountStatus.APPROVAL_REQUEST);
        assertThat(findAccount1.getAccountStatus()).isEqualTo(AccountStatus.APPROVED);
        assertThat(findAccount2.getAccountStatus()).isEqualTo(AccountStatus.APPROVED);
    }


}