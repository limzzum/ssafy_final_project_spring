package com.ssafy.enjoytrip.configuration;

import com.ssafy.enjoytrip.interceptor.LoginCheckInterCeptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterCeptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/api/user/regist", "/api/user/login", "/api/user/logout/**","/api/place/**",
//                        "/**/*.css","/**/*.jpg","/**/*.jpeg","/**/*.png","/**/*.peg", "/**/*.js", "/error/**",
//                        "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**");
//    }

    @Bean
    public LoginCheckInterCeptor loginCheckInterCeptor(){
        return new LoginCheckInterCeptor();
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/swagger-ui/**")
////                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//    }

}
