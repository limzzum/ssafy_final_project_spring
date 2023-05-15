package com.ssafy.enjoytrip.configuration;

import com.ssafy.enjoytrip.interceptor.LoginCheckInterCeptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterCeptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/api/user/regist", "/api/user/login", "/api/user/logout",
                        "/css/**", "/*.ico", "/error/**");
    }
    @Bean
    public LoginCheckInterCeptor loginCheckInterCeptor(){
        return new LoginCheckInterCeptor();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
