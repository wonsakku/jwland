package com.jwland.jwland.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class AccountTest {


    @PersistenceContext
    EntityManager em;

    @Test
    void test(){

        //given
        Account account = new Account("loginId", "name", "password", "grade", "school");
        LocalDateTime beforePersist = LocalDateTime.now();

        em.persist(account);

        em.flush();
        em.clear();

        // when
        Account findMember = em.find(Account.class, account.getId());

        // then
        assertThat(findMember.getModifiedDateTime()).isAfterOrEqualTo(beforePersist);
        assertThat(findMember.getCreatedDateTime()).isAfterOrEqualTo(beforePersist);
    }
}
