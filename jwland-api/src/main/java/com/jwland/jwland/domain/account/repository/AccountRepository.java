package com.jwland.jwland.domain.account.repository;

import com.jwland.jwland.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLoginId(String name);
}
