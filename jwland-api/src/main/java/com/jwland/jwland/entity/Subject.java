package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@EqualsAndHashCode(of = {"id"})
public class Subject extends BaseEntity{

    private static final String Y = "Y";
    private static final String N = "N";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name")
    private String name;
    private String useYn;

    private Subject(String name, String useYn){
        Assert.hasText(name, "update 시 name 은 필수값입니다.");
        Assert.isTrue(Y.equals(useYn) || N.equals(useYn), "useYn은 'Y' 또는 'N' 이어야 합니다.");
        this.name = name;
        this.useYn = useYn;
    }

    public Subject(Long id, String name, String useYn){
        Assert.notNull(id, "update 시 subjectId 는 필수값입니다.");
        Assert.hasText(name, "update 시 name 은 필수값입니다.");
        Assert.isTrue(Y.equals(useYn) || N.equals(useYn), "useYn은 'Y' 또는 'N' 이어야 합니다.");
        this.id = id;
        this.name = name;
        this.useYn = useYn;
    }

    public static Subject toInsertEntity(String name, String useYn) {
        return new Subject(name, useYn);
    }

    public static Subject toUpdateEntity(Long id, String name, String useYn) {
        return new Subject(id, name, useYn);
    }

    public void changeData(Subject changing){

        if(!this.id.equals(changing.id)){
            throw new IllegalArgumentException("subjectId 가 서로 같지 않습니다.");
        }

        Assert.hasText(changing.name);
        Assert.isTrue(changing.useYn.equals(Y) || changing.useYn.equals(N));

        this.name = changing.name;
        this.useYn = changing.useYn;
    }

    public void assignId(Long subjectId) {
        this.id = subjectId;
    }
}





















