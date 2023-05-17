package com.jwland.jwland.domain.account.dto;

import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.entity.status.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class AccountDto {

    private String id;
    @NotNull(message = "이름은 필수값입니다.")
    private String name;
    
    @NotNull(message = "아이디는 필수값입니다.")
    private String loginId;
    
    @NotNull(message = "비밀번호는 필수값입니다.")
//    @Length(min = 8, max = 20, message = "비밀번호는 8~20자 입니다.")
    private String password;
    
    @NotNull(message = "학교 선택은 필수입니다.")
    private Long schoolId;

    private String approved;

    @NotNull(message = "학년 선택은 필수입니다.")
    private String gradeNumber;

    public AccountDto(String name, String loginId, String password, Long schoolId, String gradeNumber) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.schoolId = schoolId;
        this.gradeNumber = gradeNumber;
    }

    public Account toInsertEntity(String encodedPassword, School school) {
        return Account.insertEntity(this.loginId, this.name, encodedPassword, this.gradeNumber, AccountStatus.APPROVAL_REQUEST, school);
    }
    public Account toInsertEntity(String encodedPassword, AccountStatus accountStatus, School school) {
        return Account.insertEntity(this.loginId, this.name, encodedPassword, this.gradeNumber, accountStatus, school);
    }
}
