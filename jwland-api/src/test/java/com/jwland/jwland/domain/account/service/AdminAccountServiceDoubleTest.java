package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountStatusUpdateDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.status.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdminAccountServiceDoubleTest {

    private AccountRepository accountRepository;
    private AdminAccountService accountService;

    @BeforeEach
    void init(){
        accountRepository = new MemAccountRepository();
        accountService = new AdminAccountService(accountRepository);
    }

    @ParameterizedTest
    @ValueSource(strings = {"APPROVED", "DELETED","DORMANT","APPROVAL_REQUEST"})
    @DisplayName("account 상태 업데이트 테스트")
    void update_Account_Status_Test(String status){

        // given
        final Account account1 = accountRepository.save(new Account("id1", "pass1"));
        final Account account2 = accountRepository.save(new Account("id2", "pass2"));
        List<Long> accountIds = List.of(account1.getId(), account2.getId());
        final AccountStatusUpdateDto accountStatusUpdateDto = new AccountStatusUpdateDto(accountIds, status);

        // when
        accountService.updateAccountStatus(accountStatusUpdateDto);

        // then
        assertThat(account1.getAccountStatus()).isEqualTo(AccountStatus.findByName(status));
        assertThat(account2.getAccountStatus()).isEqualTo(AccountStatus.findByName(status));
    }




}



