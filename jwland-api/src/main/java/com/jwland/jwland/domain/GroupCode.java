package com.jwland.jwland.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@Entity
public class GroupCode extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "group_code_id")
    private Long id;
    private String code;
    private String name;
    private String description;

}
