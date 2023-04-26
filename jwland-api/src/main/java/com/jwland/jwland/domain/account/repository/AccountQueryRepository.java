package com.jwland.jwland.domain.account.repository;

import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.status.AccountStatus;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountQueryRepository {

    Page<Account> findAccountsWithConditions(Pageable pageable, String name, AccountStatus accountStatus);

    Page<Account> findAccountsNotInLesson(Long lessonId, SchoolClassification schoolClassification, Grade grade, String name, Pageable pageable);
}
