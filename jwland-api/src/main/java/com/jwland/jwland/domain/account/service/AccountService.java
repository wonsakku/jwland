package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.dto.AuthenticatedAccount;
import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.domain.account.repository.SchoolRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public Long join(AccountDto accountDto) {

        accountRepository.findByLoginId(accountDto.getLoginId()).ifPresent((account) -> {
            throw new IllegalStateException("존재하는 아이디입니다.");
        });

        final School school = schoolRepository.findById(accountDto.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학교입니다."));

        String encodedPassword = passwordEncoder.encode(accountDto.getPassword());

        Account saved = accountRepository.save(accountDto.toInsertEntity(encodedPassword, school));

        return saved.getId();
    }

    public String login(LoginDto loginDto) {

        // 인증 토큰을 생성하기 위해선 userDetailsService 구현체에서 loadUserByUsername 을 구현해야 한다.
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.createToken(authenticate);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findAccountByLoginIdIncludeRoles(username)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 정보가 없습니다."));

        return new AuthenticatedAccount(account);
    }
}




