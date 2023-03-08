package com.jwland.jwlandapi.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = Constant.SCHEMA_JWLAND)
public class Account extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gradeCode;

    @Column(nullable = false)
    private String schoolCode;

//    private Set<RoleType> roles = new HashSet<>();
}

