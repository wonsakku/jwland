package com.jwland.jwland.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class JpaConfig {

    // TODO : AuditorAware 완성하기!
    @Bean
    public AuditorAware<Long> auditorProvider(){
        return () -> Optional.of(99999999999L);
    }

}
