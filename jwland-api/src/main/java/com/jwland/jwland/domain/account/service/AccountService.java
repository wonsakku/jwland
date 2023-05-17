package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.School;
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

    public Long join(AccountDto accountDto, School school) {

        accountRepository.findByLoginId(accountDto.getLoginId()).ifPresent((account) -> {
            throw new IllegalStateException("존재하는 아이디입니다.");
        });

        String encodedPassword = passwordEncoder.encode(accountDto.getPassword());
        Account saved = accountRepository.save(accountDto.toInsertEntity(encodedPassword, school));

        return saved.getId();
    }

}




