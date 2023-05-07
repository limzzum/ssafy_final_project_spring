package com.ssafy.enjoytrip.configuration;

import com.ssafy.enjoytrip.model.mapper.UserMapper;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = UserMapper.class)
public class ApplicationConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(@Value(value = "${jasypt.secrete-key}") String secreteKey){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(secreteKey);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPoolSize(1);
        encryptor.setConfig(config);
        return encryptor;
    }

}