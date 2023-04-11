package com.jwland.jwland.config;

import com.jwland.jwland.constant.CommonCode;
import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.entity.*;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.LessonStatus;
import com.jwland.jwland.entity.status.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


@Profile("local")
@RequiredArgsConstructor
@Configuration
public class InitConfig {

    private final InitData initData;

    @PostConstruct
    public void init(){
        initData.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitData{
        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;


        @Transactional
        public void init() {

            GroupCode gc2 = new GroupCode(CommonCode.GroupCode.SCHOOL_CODE.getCode(), "학교명", "");

            em.persist(gc2);

            DetailCode dc20 = new DetailCode(gc2, "G0200", "재원랜드");
            DetailCode dc21 = new DetailCode(gc2, "G0201", "동아고");
            DetailCode dc22 = new DetailCode(gc2, "G0202", "구덕고");
            DetailCode dc23 = new DetailCode(gc2, "G0203", "경남고");

            em.persist(dc20);
            em.persist(dc21);
            em.persist(dc22);
            em.persist(dc23);

            Subject subject1 = Subject.toInsertEntity("생명과학1", "Y");
            Subject subject2 = Subject.toInsertEntity("생명과학2", "Y");
            Subject subject3 = Subject.toInsertEntity("화학1", "Y");
            Subject subject4 = Subject.toInsertEntity("화학2", "Y");

            em.persist(subject1);
            em.persist(subject2);
            em.persist(subject3);
            em.persist(subject4);

            Lesson lesson1 = Lesson.toInsertEntity("테스트", Grade.HIGH_3.name(), subject1, "20230403", LessonStatus.OPEN.name());
            Lesson lesson2 = Lesson.toInsertEntity("테스트2", Grade.HIGH_2.name(), subject1, "20230415", LessonStatus.BEFORE_OPEN.name());
            Lesson lesson3 = Lesson.toInsertEntity("테스트3", Grade.HIGH_1.name(), subject1, "20230410", LessonStatus.BEFORE_OPEN.name());

            em.persist(lesson1);
            em.persist(lesson2);
            em.persist(lesson3);

            final AccountDto accountDto = new AccountDto("test", "test", "test", dc20.getCode(), Grade.HIGH_3.name());
            final String encoded = passwordEncoder.encode(accountDto.getPassword());
            final Account account = accountDto.toInsertEntity(encoded);

            em.persist(account);

            final AccountRole accountRole1 = new AccountRole(account, RoleType.ROLE_ADMIN);
            final AccountRole accountRole2 = new AccountRole(account, RoleType.ROLE_USER);

            em.persist(accountRole1);
            em.persist(accountRole2);

        }
    }



}











