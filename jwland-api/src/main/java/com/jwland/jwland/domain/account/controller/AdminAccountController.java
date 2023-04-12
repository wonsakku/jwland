package com.jwland.jwland.domain.account.controller;

import com.jwland.jwland.domain.account.dto.AccountStatusUpdateDto;
import com.jwland.jwland.domain.account.dto.AccountsDto;
import com.jwland.jwland.domain.account.service.AdminAccountService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/accounts")
public class AdminAccountController {

    private final AdminAccountService adminAccountService;

    @GetMapping
    public ResponseEntity<DefaultResponseDto<Page<AccountsDto>>> test(
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })  Pageable pageable){
        final Page<AccountsDto> data = adminAccountService.findAccounts(pageable);
        return ResponseEntity.ok(new DefaultResponseDto<>( HttpStatus.OK, data ) );
    }

    @PutMapping("/account-status/update")
    public ResponseEntity<DefaultResponseDto> updateAccountStatus(@RequestBody @Validated AccountStatusUpdateDto accountStatusUpdateDto, Errors errors){

        ErrorUtil.validate(errors);
        adminAccountService.updateAccountStatus(accountStatusUpdateDto);

        return ResponseEntity.ok( new DefaultResponseDto<>(HttpStatus.OK) );
    }



}
