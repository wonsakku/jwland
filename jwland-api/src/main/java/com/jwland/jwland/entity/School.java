package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.SchoolClassification;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "school")
public class School extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "school_id")
    private Long id;

    @Column(nullable = false, name = "school_classification")
    private SchoolClassification classification;

    @Column(nullable = false, name = "school_name")
    private String name;

    @Column(nullable = false, name = "open_yn")
    private String openYn;

    public School(SchoolClassification classification, String name, String openYn) {
        this.classification = classification;
        this.name = name;
        this.openYn = openYn;
    }
}

