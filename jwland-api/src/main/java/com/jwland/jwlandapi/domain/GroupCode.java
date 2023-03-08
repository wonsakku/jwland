package com.jwland.jwlandapi.domain;

import lombok.Getter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = Constant.SCHEMA_JWLAND)
public class GroupCode extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "group_code_id")
    private Long id;
    private String code;
    private String name;
    private String description;

}
