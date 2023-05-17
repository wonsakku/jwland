package com.jwland.jwland.domain.account.repository;

import com.jwland.jwland.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountQueryRepository{
    Optional<Account> findByLoginId(String name);

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.roles WHERE a.loginId = :loginId")
    Optional<Account> findAccountByLoginIdIncludeRoles(String loginId);

//    @Query("SELECT a FROM Account a JOIN FETCH a.school WHERE a.name LIKE :name")
    Page<Account> findAccountsByNameContaining(Pageable pageable, String name);
//    Page<Account> findAll(Pageable pageable);

    @Query("SELECT a FROM Account a WHERE a.id IN :accountIds")
    List<Account> findAccountsById(@Param("accountIds") List<Long> accountIds);
}
