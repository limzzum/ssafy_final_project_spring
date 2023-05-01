package com.ssafy.enjoytrip;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void mapperTest(){
        assertNotNull(mapper);
    }

    @Test
    void selectTest(){
        User u = mapper.select("admin");
        assertEquals(u.getUserPwd(),"1234");
    }

}
