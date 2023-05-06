package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam")
public class Exam extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "exam_master_id")
    private Long id;

    @Column(nullable = false, name = "year")
    private Integer year;

    @Column(nullable = false, name = "month")
    private Integer month;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "organization_id")
    private ExamOrganization organization;

    private Exam(Integer year, Integer month, ExamOrganization organization) {
        this.year = year;
        this.month = month;
        this.organization = organization;
    }

    public static Exam toInsertEntity(Integer year, Integer month, ExamOrganization examOrganization) {
        return new Exam(year, month, examOrganization);
    }

    public void update(Exam updating) {
        this.year = updating.year;
        this.month = updating.month;
        this.organization = updating.organization;
    }
}
