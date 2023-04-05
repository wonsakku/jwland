package com.jwland.jwland.domain.account.dto;

import com.jwland.jwland.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class AuthenticatedAccount extends User {


    public AuthenticatedAccount(Account account) {
        super(account.getLoginId(), account.getPassword(), account.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
