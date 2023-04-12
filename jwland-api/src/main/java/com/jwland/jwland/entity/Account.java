package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.AccountStatus;
import com.jwland.jwland.entity.status.Grade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND, name = "account")
@Entity
public class Account extends BaseEntity {

    private final static String Y = "Y";
    private final static String N = "N";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountRole> roles = new HashSet<>();

    private Account(String loginId, String name, String password, Grade grade, School school) {
        this(loginId, name, password, grade, AccountStatus.APPROVAL_REQUEST, school);
    }

    private Account(String loginId, String name, String password, Grade grade, AccountStatus accountStatus, School school) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.grade = grade;
        this.accountStatus = accountStatus;
        this.school = school;
    }

    public static Account insertEntity(String loginId, String name, String password, String gradeNumber, AccountStatus accountStatus, School school){
        return new Account(loginId, name, password, Grade.findByNumber(gradeNumber), accountStatus, school);
    }

    public Set<String> getRoles(){
        return this.roles.stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toSet());
    }

    public String getSchoolName() {
        return this.school.getName();
    }


    public void updateAccountStatus(String accountStatusName) {
        this.accountStatus = AccountStatus.findByName(accountStatusName);
    }
}

