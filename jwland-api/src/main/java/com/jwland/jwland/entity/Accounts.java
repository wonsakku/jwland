package com.jwland.jwland.entity;

import java.util.List;

public class Accounts {
    private final List<Account> accounts;

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account findByAccountId(Account comparing){
        return this.accounts.stream()
                .filter(account -> account.equals(comparing))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 account 입니다."));
    }
}
