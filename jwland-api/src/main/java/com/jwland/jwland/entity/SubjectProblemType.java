package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.ProblemClassification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "subject_problem_type")
public class SubjectProblemType extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "subject_problem_type_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SubjectProblemType parent;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_classification")
    private ProblemClassification problemClassification;

    @Column(nullable = false, name = "classification_name")
    private String name;

    private String description;

}


