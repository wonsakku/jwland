package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    School school;

    @BeforeEach
    void init(){
        school = new School(SchoolClassification.HIGH, "북산고", "Y");
        em.persist(school);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(school.getId());
    }

    @Test
    @DisplayName("회원가입 테스트")
    void joinTest(){

        String password = "fireman";
        AccountDto accountDto = new AccountDto("정대만", "three-pointer", password, school.getId(), Grade.THREE.name());

        Long joinedId = accountService.join(accountDto);
        Account findById = accountRepository.findById(joinedId).get();

        assertThat(password).isNotEqualTo(findById.getPassword());
        assertThat(passwordEncoder.matches(password, findById.getPassword())).isTrue();
    }

    @Test
    @DisplayName("회원가입 테스트 - 아이디 중복 예외처리")
    void join_Duplicated_loginId(){

        String password = "fireman";
        AccountDto accountDto = new AccountDto("정대만", "three-pointer", password, school.getId(), Grade.THREE.name());

        Long joinedId = accountService.join(accountDto);

        assertThatThrownBy(() -> {
            accountService.join(accountDto);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("존재하는 아이디입니다.");
    }



}