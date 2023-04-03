package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Long join(AccountDto accountDto) {

        String loginId = accountDto.getLoginId();
        accountRepository.findByLoginId(loginId).ifPresent((account) -> {
            throw new IllegalStateException("존재하는 아이디입니다.");
        });

        String password = accountDto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        Account account = accountDto.toInsertEntity(encodedPassword);
        Account saved = accountRepository.save(account);

        return saved.getId();
    }
}
