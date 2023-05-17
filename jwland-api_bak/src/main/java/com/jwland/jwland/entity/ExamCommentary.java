package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam_commentary")
public class ExamCommentary extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "exam_commentary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "exam_subject_id")
    private ExamSubject examSubject;

    @Column(nullable = false, name = "exam_commentary_url")
    private String url;

    @Column(nullable = false, name = "exam_commentary_name")
    private String name;

    private String description;
}
