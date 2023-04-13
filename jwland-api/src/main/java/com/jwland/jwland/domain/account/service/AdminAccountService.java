package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountStatusUpdateDto;
import com.jwland.jwland.domain.account.dto.AccountsDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.status.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminAccountService {

    private final AccountRepository accountRepository;


    public Page<AccountsDto> findAccounts(Pageable pageable, String name, String accountStatus) {
        AccountStatus _accountStatus = StringUtils.hasText(accountStatus) ? AccountStatus.findByName(accountStatus) : null;
        Page<Account> result = accountRepository.findAccountsWithConditions(pageable, name, _accountStatus);
        return result.map(AccountsDto::new);
    }

    @Transactional
    public void updateAccountStatus(AccountStatusUpdateDto accountStatusUpdateDto) {
        List<Account> accounts = accountRepository.findAccountsById(accountStatusUpdateDto.getAccountIds());
        accounts.forEach(account -> account.updateAccountStatus(accountStatusUpdateDto.getAccountStatusName()));
    }
}
