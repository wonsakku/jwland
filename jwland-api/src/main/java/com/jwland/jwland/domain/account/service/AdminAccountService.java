package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountStatusUpdateDto;
import com.jwland.jwland.domain.account.dto.AccountsDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminAccountService {

    private final AccountRepository accountRepository;


    public Page<AccountsDto> findAccounts(Pageable pageable, String name, String accountStatus) {
        final Page<Account> data = accountRepository.findAccountsByNameContaining(pageable, name);
//        final Page<Account> data = accountRepository.findAll(pageable);
        return data.map(AccountsDto::new);
    }

    @Transactional
    public void updateAccountStatus(AccountStatusUpdateDto accountStatusUpdateDto) {
        List<Account> accounts = accountRepository.findAccountsById(accountStatusUpdateDto.getAccountIds());
        accounts.forEach(account -> account.updateAccountStatus(accountStatusUpdateDto.getAccountStatusName()));
    }
}
