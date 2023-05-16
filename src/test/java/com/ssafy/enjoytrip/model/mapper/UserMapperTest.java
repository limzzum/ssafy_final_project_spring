package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
        User u = mapper.selectByUserId("admin");
        assertEquals(u.getUserPwd(),"1234");
    }

    @Test
    @Transactional
    void insertTest(){
        User u = User.builder().userId("testUser").userName("김테스트").userPwd("1234").email("test@ssafy.com").build();
        mapper.insert(u);
        assertEquals("1234",mapper.selectByUserId("testUser").getUserPwd());
    }

    @Test
    @Transactional
    void deleteTest(){
        User u = User.builder().userId("testUser").userName("김테스트").userPwd("1234").email("test@ssafy.com").build();
        mapper.insert(u);
        assertNotNull(mapper.selectByUserId(u.getUserId()));
        mapper.delete(u.getUserNo());
        assertNull(mapper.selectByUserId(u.getUserId()));
    }

    @Test
    @Transactional
    void updateTest(){
        User u = User.builder().userId("testUser").userName("김테스트").userPwd("1234").email("test@ssafy.com").build();
        mapper.insert(u);
        assertEquals(mapper.selectByUserId(u.getUserId()).getUserName(),"김테스트");
        u.setUserName("수정됨");
        assertNotEquals(mapper.selectByUserId(u.getUserId()).getUserName(),"수정됨");
        mapper.update(u);
        assertEquals(mapper.selectByUserId(u.getUserId()).getUserName(),"수정됨");
    }

}
