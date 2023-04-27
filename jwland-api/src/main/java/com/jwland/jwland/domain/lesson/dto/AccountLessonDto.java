package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountLessonDto {

    private Long accountId;
    private String name;
    private String schoolName;
    private String grade;

    public AccountLessonDto(Account account){
        this.accountId = account.getId();
        this.name = account.getName();
        this.schoolName = account.getSchoolName();
        this.grade = account.getGrade().getGrade();
    }

}
