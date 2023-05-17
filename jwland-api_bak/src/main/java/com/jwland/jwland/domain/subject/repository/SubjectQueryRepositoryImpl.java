package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.QExamSubject;
import com.jwland.jwland.entity.QSubject;
import com.jwland.jwland.entity.Subject;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jwland.jwland.entity.QExamSubject.*;
import static com.jwland.jwland.entity.QSubject.*;

@Repository
public class SubjectQueryRepositoryImpl implements SubjectQueryRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public SubjectQueryRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Subject> findSubjectsUnenrolledInExam(Long examId) {
        final QExamSubject innerExamSubject = new QExamSubject("innerExamSubject");
        return queryFactory.select(subject)
                .from(subject)
                .leftJoin(examSubject)
                .on(examSubject.exam.id.eq(examId), examSubject.subject.eq(subject))
                .where(subject.notIn(
                            JPAExpressions.select(innerExamSubject.subject)
                                    .from(innerExamSubject)
                                    .where(innerExamSubject.exam.id.eq(examId))
                        )
                )
                .orderBy(subject.id.asc())
                .fetch();
    }
}








