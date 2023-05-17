package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.ExamSubject;
import com.jwland.jwland.entity.QExamSubject;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jwland.jwland.entity.QExamSubject.*;

@Repository
public class ExamSubjectQueryRepositoryImpl implements ExamSubjectQueryRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ExamSubjectQueryRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ExamSubject> findExamSubjectsByExamId(Long examId) {
        return queryFactory.selectFrom(examSubject)
                .join(examSubject.subject).fetchJoin()
                .where(examSubject.exam.id.eq(examId))
                .orderBy(examSubject.subject.id.asc())
                .fetch()
                ;
    }
}
