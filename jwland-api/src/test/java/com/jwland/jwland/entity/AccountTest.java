package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.Grade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        String password = "password";
        Account account = Account.insertEntity("loginId", "name", password, Grade.HIGH_2.name(), "school");
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
