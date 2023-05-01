package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Transactional
    void insertTest(){
        User u = User.builder().userId("testUser").userName("김테스트").userPwd("1234").email("test@ssafy.com").build();
        mapper.insert(u);
        assertEquals("1234",mapper.select("testUser").getUserPwd());
    }

}
