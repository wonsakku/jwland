package com.jwland.jwlandapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@Entity
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

    public Account(String loginId, String name, String password, String gradeCode, String schoolCode) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.gradeCode = gradeCode;
        this.schoolCode = schoolCode;
    }

    //    private Set<RoleType> roles = new HashSet<>();
}

