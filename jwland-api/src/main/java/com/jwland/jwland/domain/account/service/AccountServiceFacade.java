package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountServiceFacade {

    private final AccountService accountService;
    private final SchoolService schoolService;
    private final LoginProcessService loginProcessService;


    @Transactional
    public void join(AccountDto accountDto){
        final School school = schoolService.findSchoolById(accountDto.getSchoolId());
        accountService.join(accountDto, school);
    }


    public String login(LoginDto loginDto) {
        return loginProcessService.login(loginDto);
    }
}
