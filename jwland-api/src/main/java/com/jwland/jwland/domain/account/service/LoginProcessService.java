package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.dto.LoginDto;

public interface LoginProcessService {

    String login(LoginDto loginDto);
}
