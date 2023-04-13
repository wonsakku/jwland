package com.jwland.jwland.domain.account.repository;

import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.status.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountQueryRepository {

    Page<Account> findAccountsWithConditions(Pageable pageable, String name, AccountStatus accountStatus);
}
