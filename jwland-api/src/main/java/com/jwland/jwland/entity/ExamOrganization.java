package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.ExamOrganizationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam_organization")
public class ExamOrganization extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "exam_organization_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "exam_organization_type")
    private ExamOrganizationType examOrganizationType;

    @Column(nullable = false, name = "organization_name")
    private String name;

    private Integer seq;


    private ExamOrganization(ExamOrganizationType examOrganizationType, String name, Integer seq){
        this.examOrganizationType = examOrganizationType;
        this.name = name;
        this.seq = seq;
    }

    public static ExamOrganization insertEntity(ExamOrganizationType examOrganizationType, String name, Integer seq){
        return new ExamOrganization(examOrganizationType, name, seq);
    }
}


