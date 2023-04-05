package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
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
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    private String schoolCode;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountRole> roles = new HashSet<>();

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

    public Set<String> getRoles(){
        return this.roles.stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toSet());
    }

}

