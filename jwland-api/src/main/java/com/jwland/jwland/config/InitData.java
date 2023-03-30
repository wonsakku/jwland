package com.jwland.jwland.config;

import com.jwland.jwland.entity.DetailCode;
import com.jwland.jwland.entity.GroupCode;
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
        GroupCode gc3 = new GroupCode("G03", "과목", "");
        GroupCode gc4 = new GroupCode("G04", "수업종류", "");
        GroupCode gc5 = new GroupCode("G05", "수업_진행_상태", "");

        em.persist(gc1);
        em.persist(gc2);
        em.persist(gc3);
        em.persist(gc4);
        em.persist(gc5);

        DetailCode dc10 = new DetailCode(gc1, "G0100", "재원랜드");
        DetailCode dc11 = new DetailCode(gc1, "G0101", "중1");
        DetailCode dc12 = new DetailCode(gc1, "G0102", "중2");
        DetailCode dc13 = new DetailCode(gc1, "G0103", "중3");
        DetailCode dc14 = new DetailCode(gc1, "G0104", "고1");
        DetailCode dc15 = new DetailCode(gc1, "G0105", "고2");
        DetailCode dc16 = new DetailCode(gc1, "G0106", "고3");
        DetailCode dc17 = new DetailCode(gc1, "G0107", "N수생");

        em.persist(dc10);
        em.persist(dc11);
        em.persist(dc12);
        em.persist(dc13);
        em.persist(dc14);
        em.persist(dc15);
        em.persist(dc16);
        em.persist(dc17);

        DetailCode dc20 = new DetailCode(gc2, "G0200", "재원랜드");
        DetailCode dc21 = new DetailCode(gc2, "G0201", "동아고");
        DetailCode dc22 = new DetailCode(gc2, "G0202", "구덕고");
        DetailCode dc23 = new DetailCode(gc2, "G0203", "경남고");

        em.persist(dc20);
        em.persist(dc21);
        em.persist(dc22);
        em.persist(dc23);


        DetailCode dc31 = new DetailCode(gc3, "G0301", "생명과학1");
        DetailCode dc32 = new DetailCode(gc3, "G0302", "생명과학2");
        DetailCode dc33 = new DetailCode(gc3, "G0303", "화학1");
        DetailCode dc34 = new DetailCode(gc3, "G0304", "화학2");

        em.persist(dc31);
        em.persist(dc32);
        em.persist(dc33);
        em.persist(dc34);

        DetailCode gc41 = new DetailCode(gc4, "G0401", "고2 생명과학1");
        DetailCode gc42 = new DetailCode(gc4, "G0402", "고2 생명과학2");
        DetailCode gc43 = new DetailCode(gc4, "G0403", "고2 화학1");
        DetailCode gc44 = new DetailCode(gc4, "G0404", "고2 화학2");
        DetailCode gc45 = new DetailCode(gc4, "G0405", "고3 생명과학1");
        DetailCode gc46 = new DetailCode(gc4, "G0406", "고3 생명과학2");
        DetailCode gc47 = new DetailCode(gc4, "G0407", "고3 화학1");
        DetailCode gc48 = new DetailCode(gc4, "G0408", "고3 화학2");

        em.persist(gc41);
        em.persist(gc42);
        em.persist(gc43);
        em.persist(gc44);
        em.persist(gc45);
        em.persist(gc46);
        em.persist(gc47);
        em.persist(gc48);

        DetailCode gc51 = new DetailCode(gc5, "G0501", "개강전");
        DetailCode gc52 = new DetailCode(gc5, "G0502", "진행중");
        DetailCode gc53 = new DetailCode(gc5, "G0503", "완강");

        em.persist(gc51);
        em.persist(gc52);
        em.persist(gc53);

    }
}
