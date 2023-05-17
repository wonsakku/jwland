package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AuthenticatedAccount;
import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginProcessServiceImpl implements LoginProcessService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

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
