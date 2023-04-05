package com.jwland.jwland.domain.account.controller;

import com.jwland.jwland.domain.account.dto.AccountDto;
import com.jwland.jwland.domain.account.dto.LoginDto;
import com.jwland.jwland.domain.account.service.AccountService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.jwt.JwtConstants;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/join")
    public ResponseEntity<DefaultResponseDto> join(@RequestBody @Validated AccountDto accountDto, Errors errors){

        ErrorUtil.validate(errors);
        accountService.join(accountDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new DefaultResponseDto(HttpStatus.CREATED) );
    }

    @PostMapping("/login")
    public ResponseEntity<DefaultResponseDto> login(@RequestBody LoginDto loginDto){

        String token = accountService.login(loginDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtConstants.AUTHORIZATION, JwtConstants.BEARER + token);

        return  ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(new DefaultResponseDto(HttpStatus.OK));
    }

}


