package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.status.AccountStatus;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class MemAccountRepository implements AccountRepository {

    private Map<Long, Account> accounts = new HashMap<>();
    private Long id = 0l;

    @Override
    public Optional<Account> findByLoginId(String loginId) {
        final Set<Long> keys = accounts.keySet();
        for (Long key : keys) {
            final Account account = accounts.get(key);
            if(account.getLoginId().equals(loginId)){
                return Optional.of(accounts.get(key));
            }
        }
        return Optional.empty();
    }

    @Override
    public <S extends Account> S save(S entity) {
        id++;
        entity.setIdForTest(id);
        accounts.put(id, entity);
        return entity;
    }

    @Override
    public Optional<Account> findById(Long aLong) {
        return Optional.empty();
    }




    @Override
    public Optional<Account> findAccountByLoginIdIncludeRoles(String loginId) {
        return Optional.empty();
    }

    @Override
    public Page<Account> findAccountsByNameContaining(Pageable pageable, String name) {
        return null;
    }

    @Override
    public List<Account> findAccountsById(List<Long> accountIds) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Account> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Account entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Account> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Account getOne(Long aLong) {
        return null;
    }

    @Override
    public Account getById(Long aLong) {
        return null;
    }

    @Override
    public Account getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Account> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Account> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Account> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Page<Account> findAccountsWithConditions(Pageable pageable, String name, AccountStatus accountStatus) {
        return null;
    }

    @Override
    public Page<Account> findAccountsNotInLesson(Long lessonId, SchoolClassification schoolClassification, Grade grade, String name, Pageable pageable) {
        return null;
    }
}
