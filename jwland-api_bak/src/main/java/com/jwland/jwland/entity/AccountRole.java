package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"account"})
@Entity
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "account_role")
public class AccountRole extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private RoleType role;


    public AccountRole(Account account, RoleType role) {
        this.account = account;
        this.role = role;
    }
}
