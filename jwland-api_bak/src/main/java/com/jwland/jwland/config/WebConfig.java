package com.jwland.jwland.config;

import com.jwland.jwland.jwt.JwtConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final String UI_SERVER;

    public WebConfig(@Value("${ui-server}") String UI_SERVER) {
        this.UI_SERVER = UI_SERVER;
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins(UI_SERVER)
//                .allowedMethods("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name()
                ).allowCredentials(false)
                .exposedHeaders(JwtConstants.AUTHORIZATION)
                .maxAge(5000);
    }

}
