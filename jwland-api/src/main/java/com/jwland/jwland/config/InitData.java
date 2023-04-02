package com.jwland.jwland.config;

import com.jwland.jwland.entity.DetailCode;
import com.jwland.jwland.entity.GroupCode;
import com.jwland.jwland.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
public class InitData {


    private final EntityManager em;

    @Transactional
    public void init() {

        GroupCode gc1 = new GroupCode("G01", "학년", "");
        GroupCode gc2 = new GroupCode("G02", "학교명", "");

        em.persist(gc1);
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

    }
}
