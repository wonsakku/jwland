package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.School;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;

class AccountServiceFacadeDoubleTest {

    AccountServiceFacade accountServiceFacade;

    SchoolService mockSchoolService = Mockito.mock(SchoolService.class);
    LoginProcessService mockLoginProcessService;
    private BCryptPasswordEncoder passwordEncoder;
    private AccountRepository fakeAccountRepository;

    @BeforeEach
    void init(){
        fakeAccountRepository = new MemAccountRepository();
        passwordEncoder = new BCryptPasswordEncoder();
        AccountService stubAccountService = new AccountService(fakeAccountRepository, passwordEncoder);
        mockLoginProcessService = new StubLoginProcessService(fakeAccountRepository, passwordEncoder);
        accountServiceFacade = new AccountServiceFacade(stubAccountService, mockSchoolService, mockLoginProcessService);
    }

    @Test
    @DisplayName("회원가입 성공")
    void joinTest(){

        // given
        final long schoolId = 1L;
        BDDMockito.given(mockSchoolService.findSchoolById(schoolId))
                .willReturn(new School(schoolId, SchoolClassification.HIGH, "테스트 스쿨", "Y"));
        final AccountDto accountDto = new AccountDto("name", "loginId", "password", schoolId, Grade.ONE.name());

        // when
        accountServiceFacade.join(accountDto);
        final Account joined = fakeAccountRepository.findByLoginId(accountDto.getLoginId()).get();

        // then
        assertThat(joined).isNotNull();
        assertThat(joined.getName()).isEqualTo(accountDto.getName());
        assertThat(passwordEncoder.matches(accountDto.getPassword(), joined.getPassword())).isTrue();
        assertThat(joined.getSchool().getId()).isEqualTo(schoolId);
    }

    @Test
    @DisplayName("아이디 중복 예외처리")
    void dup_Id_Test(){
        // given
        final String accountLoginId = "duplicatedLoginId";
        fakeAccountRepository.save(new Account(accountLoginId, "password"));
        final long schoolId = 1L;
        BDDMockito.given(mockSchoolService.findSchoolById(schoolId))
                .willReturn(new School(schoolId, SchoolClassification.HIGH, "테스트 스쿨", "Y"));
        final AccountDto accountDto = new AccountDto("name", accountLoginId, "password", schoolId, Grade.ONE.name());

        // when then
        assertThatThrownBy(() -> {
            accountServiceFacade.join(accountDto);
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("로그인 테스트")
    void login_Test(){

        // given
        String loginId = "loginId";
        String password = "password";
        fakeAccountRepository.save(new Account(loginId, passwordEncoder.encode(password)));

        // when
        final String token = accountServiceFacade.login(new LoginDto(loginId, password));

        // then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("로그인 - 아이디가 존재하지 않는 경우")
    void login_NotFound_LoginId(){

        // given
        String loginId = "loginId";
        String password = "password";
        fakeAccountRepository.save(new Account(loginId, passwordEncoder.encode(password)));

        // when
        assertThatThrownBy(() -> {
            accountServiceFacade.login(new LoginDto(loginId + "1", password));
        }).isInstanceOf(UsernameNotFoundException.class);
    }



    @Test
    @DisplayName("로그인 - 비밀번호가 일치하지 않는 경우")
    void login_Not_Correspond_Password(){

        // given
        String loginId = "loginId";
        String password = "password";
        fakeAccountRepository.save(new Account(loginId, passwordEncoder.encode(password)));

        // when
        assertThatThrownBy(() -> {
            accountServiceFacade.login(new LoginDto(loginId, password + "1"));
        }).isInstanceOf(UsernameNotFoundException.class);
    }






}





