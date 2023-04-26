package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.AccountLessonEnrollStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountLessonEnrollStatusDto {

    private Long accountId;
    private String name;
    private String schoolName;
    private String grade;

    public AccountLessonEnrollStatusDto(Account account){
        this.accountId = account.getId();
        this.name = account.getName();
        this.schoolName = account.getSchoolName();
        this.grade = account.getGrade().getGrade();
    }

}
