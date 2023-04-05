package com.jwland.jwland.domain.account.repository;

import com.jwland.jwland.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLoginId(String name);

    @Query("SELECT a FROM Account a JOIN FETCH a.roles WHERE a.loginId = :loginId")
    Optional<Account> findAccountByLoginIdIncludeRoles(String loginId);
}
