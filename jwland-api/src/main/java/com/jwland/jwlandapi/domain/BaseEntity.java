package com.jwland.jwlandapi.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "insert_account_id")
    private Long createdBy;
    private LocalDateTime createdDateTime;
    @Column(name = "update_account_id")
    private String modifiedBy;
    private LocalDateTime modifiedDateTime;


}
