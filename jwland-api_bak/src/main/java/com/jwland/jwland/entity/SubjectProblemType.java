package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.ProblemClassification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Integer orderSequence;

    @Column(nullable = false, name = "use_yn")
    private String useYn;

    private SubjectProblemType(Subject subject, SubjectProblemType parent, ProblemClassification problemClassification, String problemTypeName, Integer orderSequence, String useYn) {

        validateAbleNotToHaveParent(parent, problemClassification);

        this.subject = subject;
        this.parent = parent;
        this.name = problemTypeName;
        this.orderSequence = orderSequence;
        this.useYn = useYn;
        this.problemClassification = problemClassification;
    }

    private SubjectProblemType(String problemTypeName, Integer orderSequence, String useYn) {
        this.name = problemTypeName;
        this.orderSequence = orderSequence;
        this.useYn = useYn;
    }

    public static SubjectProblemType toUpdateEntity(String problemTypeName, Integer orderSequence, String useYn) {
        return new SubjectProblemType(problemTypeName, orderSequence, useYn);
    }

    private void validateAbleNotToHaveParent(SubjectProblemType parent, ProblemClassification problemClassification){

        if(problemClassification.topLevel()){
            return ;
        }

        if(parent == null){
            throw new IllegalArgumentException("상위 분류값(parent)가 필요합니다.");
        }
    }


    public static SubjectProblemType toInsertEntity(Subject subject, SubjectProblemType parent, ProblemClassification problemClassification, String problemTypeName, Integer orderSequence, String useYn) {
        return new SubjectProblemType(subject, parent, problemClassification, problemTypeName, orderSequence, useYn);
    }

    public void update(SubjectProblemType updating) {
        this.name = updating.getName();
        this.useYn = updating.getUseYn();
        this.orderSequence = updating.getOrderSequence();
    }

}


