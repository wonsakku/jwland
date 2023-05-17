package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam_rank")
public class ExamRank extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "exam_rank_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "exam_subject_id")
    private ExamSubject examSubject;

    @Column(nullable = false, name = "rank")
    private Integer rank;

    @Column(name = "raw_score")
    private Integer rawScore; // 원점수

    @Column(name = "standard_score")
    private Integer standardScore; // 표준점수

}



