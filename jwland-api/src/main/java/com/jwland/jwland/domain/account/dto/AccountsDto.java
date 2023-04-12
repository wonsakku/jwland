package com.jwland.jwland.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jwland.jwland.constant.Time;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.status.Grade;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto {
    private Long id;
    private String name;
    private String loginId;
    private String school;
    private String grade;

    private String accountStatus;
    @JsonFormat(pattern = Time.Pattern.DEFAULT_PATTERN, timezone = Time.Zone.ASIA_SEOUL)
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern = Time.Pattern.DEFAULT_PATTERN, timezone = Time.Zone.ASIA_SEOUL)
    private LocalDateTime modifiedDateTime;


    public AccountsDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.loginId = account.getLoginId();
        this.school = account.getSchoolName();
        this.grade = account.getGrade().getGrade();
        this.accountStatus = account.getAccountStatus().getStatus();
        this.createdDateTime = account.getCreatedDateTime();
        this.modifiedDateTime = account.getModifiedDateTime();
//        this.grade = Grade.findByName(account.getSchoolCode()).getGrade();
    }
}
