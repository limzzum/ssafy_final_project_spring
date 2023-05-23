package com.ssafy.enjoytrip.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Slf4j
class PlaceMapperTest {

    @Autowired
    PlaceMapper mapper;

    @Test
    void getContentsTest(){
        log.info("getContentsTest : "+mapper.getContents());
    }


}
