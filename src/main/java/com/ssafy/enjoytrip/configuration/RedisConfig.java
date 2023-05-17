package com.ssafy.enjoytrip.configuration;

import org.springframework.beans.factory.annotation.Value;

public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;
}
