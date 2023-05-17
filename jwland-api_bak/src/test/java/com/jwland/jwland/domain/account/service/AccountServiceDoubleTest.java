package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;

public class AccountServiceDoubleTest {

    AccountRepository accountRepository;

    @BeforeEach
    void init(){
        accountRepository = new MemoryAccountRepository();

    }
}
