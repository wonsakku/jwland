package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
public class Subject extends BaseEntity{

    private static final String Y = "Y";
    private static final String N = "N";

    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name")
    private String name;
    private String useYn;

    private Subject(String name){
        this.name = Objects.requireNonNull(name, "insert 시 name 은 필수값입니다.");
        this.useYn = Y;
    }

    private Subject(Long id, String name, String useYn){
        this.id = Objects.requireNonNull(id, "update 시 subjectId 는 필수값입니다.");
        this.name = Objects.requireNonNull(name, "update 시 name 은 필수값입니다.");
        this.useYn = Objects.requireNonNull(useYn, "update 시 useYn 는 필수값입니다.");
    }

    public static Subject toInsertEntity(String name) {
        return new Subject(name);
    }

    public static Subject toUpdateEntity(Long id, String name, String useYn) {
        return new Subject(id, name, useYn);
    }

    public void changeData(Subject changing){
        this.name = changing.name;
        this.useYn = changing.useYn;
    }
}





















