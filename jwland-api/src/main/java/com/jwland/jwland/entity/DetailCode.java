package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND,
        uniqueConstraints = @UniqueConstraint(name = "detail_code_unique",
        columnNames = {"group_code", "code"})
)
@Entity
public class DetailCode extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "detail_code_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_code")
    private GroupCode groupCode;

    @Column(name = "code")
    private String code;

    private String name;

    public DetailCode(GroupCode groupCode, String code, String name) {
        this.groupCode = Objects.requireNonNull(groupCode);
        this.code = Objects.requireNonNull(code);
        this.name = Objects.requireNonNull(name);
    }
}



