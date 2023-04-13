package com.jwland.jwland.domain.account.repository;

import com.jwland.jwland.domain.account.dto.AccountsDto;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.QAccount;
import com.jwland.jwland.entity.status.AccountStatus;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.jwland.jwland.entity.QAccount.*;

@Repository
public class AccountRepositoryImpl implements AccountQueryRepository{

    private final JPQLQueryFactory queryFactory;
    private final EntityManager em;

    public AccountRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Account> findAccountsWithConditions(Pageable pageable, String name, AccountStatus accountStatus) {
        final JPQLQuery<Account> queryResult = queryFactory
                .selectFrom(account)
                .where(account.name.contains(name), accountStatusEq(accountStatus))
                .orderBy(account.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(queryResult.fetch(), pageable, queryResult::fetchCount);
    }

    private BooleanExpression accountStatusEq(AccountStatus accountStatus) {
        return accountStatus == null ? null : account.accountStatus.eq(accountStatus);
    }
}
