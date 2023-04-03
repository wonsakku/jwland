package com.jwland.jwland.domain.account.dto;

import com.jwland.jwland.entity.Account;
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
    private String schoolCode;
    @NotNull(message = "학년 선택은 필수입니다.")
    private String gradeCode;

    public AccountDto(String name, String loginId, String password, String schoolCode, String gradeCode) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.schoolCode = schoolCode;
        this.gradeCode = gradeCode;
    }

    public Account toInsertEntity(String encodedPassword) {
        return Account.insertEntity(this.loginId, this.name, encodedPassword, this.gradeCode, this.schoolCode);
    }
}
