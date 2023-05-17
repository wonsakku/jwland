package com.jwland.jwland.domain.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AccountStatusUpdateDto {

    @NotNull(message = "accountIds는 필수 입력값입니다.")
    private List<Long> accountIds;
    @NotNull(message = "accountStatusName는 필수 입력값입니다.")
    private String accountStatusName;

    public AccountStatusUpdateDto(List<Long> accountIds, String accountStatusName) {
        this.accountIds = accountIds;
        this.accountStatusName = accountStatusName;
    }

}
