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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AccountServiceDoubleTest {

    AccountService accountService;
    AccountRepository accountRepository;
    private School school;


    @BeforeEach
    void init(){
        accountRepository = new MemAccountRepository();
        accountService = new AccountService(accountRepository, new BCryptPasswordEncoder());
        school = new School(SchoolClassification.HIGH, "aaa", "동아고");
    }

    @Test
    @DisplayName("회원가입 성공")
    void join_Success_Test(){

        // given
        final String loginId = "insert";
        final AccountDto accountDto = new AccountDto("name", loginId, "password", null, Grade.ONE.name());

        // when
        final Long join = accountService.join(accountDto, school);
        final Account inserted = accountRepository.findByLoginId(loginId).get();

        // then
        Assertions.assertThat(loginId).isEqualTo(inserted.getLoginId());
    }

    @Test
    @DisplayName("회원가입 아이디 중복")
    void Dup_Login_Id(){

        // given
        final String loginId = "duplicatedLoginId";
        accountRepository.save(new Account(loginId));
        final AccountDto accountDto = new AccountDto("name", loginId, "password", null, Grade.ONE.name());

        // when, then
        Assertions.assertThatThrownBy(() -> {
            accountService.join(accountDto, school);
        }).isInstanceOf(IllegalStateException.class);
    }


}




