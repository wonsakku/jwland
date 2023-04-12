package com.jwland.jwland.config;

import com.jwland.jwland.constant.CommonCode;
import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.entity.*;
import com.jwland.jwland.entity.status.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


@Profile({"local"})
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

            final School school1 = new School(SchoolClassification.ADMIN, "재원랜드", "N");
            final School school2 = new School(SchoolClassification.HIGH, "동아고", "Y");
            final School school3 = new School(SchoolClassification.HIGH, "구덕고", "Y");
            final School school4 = new School(SchoolClassification.HIGH, "경남고", "Y");

            em.persist(school1);
            em.persist(school2);
            em.persist(school3);
            em.persist(school4);

            Subject subject1 = Subject.toInsertEntity("생명과학1", "Y");
            Subject subject2 = Subject.toInsertEntity("생명과학2", "Y");
            Subject subject3 = Subject.toInsertEntity("화학1", "Y");
            Subject subject4 = Subject.toInsertEntity("화학2", "Y");

            em.persist(subject1);
            em.persist(subject2);
            em.persist(subject3);
            em.persist(subject4);

            Lesson lesson1 = Lesson.toInsertEntity("테스트", SchoolClassification.HIGH.name(), Grade.THREE.name(), subject1, "20230403", LessonStatus.OPEN.name());
            Lesson lesson2 = Lesson.toInsertEntity("테스트2", SchoolClassification.HIGH.name(), Grade.TWO.name(), subject1, "20230415", LessonStatus.BEFORE_OPEN.name());
            Lesson lesson3 = Lesson.toInsertEntity("테스트3", SchoolClassification.HIGH.name(), Grade.ONE.name(), subject1, "20230410", LessonStatus.BEFORE_OPEN.name());

            em.persist(lesson1);
            em.persist(lesson2);
            em.persist(lesson3);

            final AccountDto accountDto = new AccountDto("test", "test", "test", school1.getId(), Grade.JWLAND.name());
            final String encoded = passwordEncoder.encode(accountDto.getPassword());
            final Account account = accountDto.toInsertEntity(encoded, AccountStatus.APPROVED, school1);

            em.persist(account);

            final AccountRole accountRole1 = new AccountRole(account, RoleType.ROLE_ADMIN);
            final AccountRole accountRole2 = new AccountRole(account, RoleType.ROLE_USER);

            em.persist(accountRole1);
            em.persist(accountRole2);

            School[] schools = {school2, school3, school4};
            String[] gradeNames = {Grade.ONE.name(), Grade.TWO.name(), Grade.THREE.name()};
            for(int i = 0 ; i < 30 ; i++){
                School school = schools[i % schools.length];
                String gradeName = gradeNames[i % gradeNames.length];
                final String password = "user" + i;
                final AccountDto accountDto1 = new AccountDto("user" + i, "user" + i, password, school.getId(), gradeName);
                final String encode = passwordEncoder.encode(password);
                final Account account1 = accountDto1.toInsertEntity(encode, school);

                em.persist(account1);

                final AccountRole accountRole = new AccountRole(account1, RoleType.ROLE_USER);
                em.persist(accountRole);
            }

        }
    }



}











