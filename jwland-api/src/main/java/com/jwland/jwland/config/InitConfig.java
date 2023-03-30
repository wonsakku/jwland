package com.jwland.jwland.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Configuration
public class InitConfig {

    @Value("${dev-status}")
    private String devStatus;

    private final InitData initData;

    @PostConstruct
    public void init(){
        if(!devStatus.equals("test")){
            return ;
        }

        initData.init();
    }


}











