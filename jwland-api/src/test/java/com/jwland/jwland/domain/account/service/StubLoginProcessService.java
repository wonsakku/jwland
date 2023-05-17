package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.AuthenticationException;
import java.util.UUID;

public class StubLoginProcessService implements LoginProcessService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public StubLoginProcessService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        final Account account = accountRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new UsernameNotFoundException(""));
        if(!passwordEncoder.matches(loginDto.getPassword(), account.getPassword())){
            throw new UsernameNotFoundException("");
        }
        return UUID.randomUUID().toString();
    }
}

