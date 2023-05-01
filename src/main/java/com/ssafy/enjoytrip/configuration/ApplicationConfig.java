package com.ssafy.enjoytrip.configuration;

import com.ssafy.enjoytrip.model.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = UserMapper.class)
public class ApplicationConfig {

}