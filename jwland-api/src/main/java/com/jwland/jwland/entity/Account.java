package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.Grade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@Entity
public class Account extends BaseEntity {

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
    private Grade grade;

    @Column(nullable = false)
    private String schoolCode;

    private Account(String loginId, String name, String password, Grade grade, String schoolCode) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.grade = grade;
        this.schoolCode = schoolCode;
    }

    public static Account insertEntity(String loginId, String name, String password, String gradeCode, String schoolCode){
        return new Account(loginId, name, password, Grade.findByName(gradeCode), schoolCode);
    }

    //    private Set<RoleType> roles = new HashSet<>();
}

