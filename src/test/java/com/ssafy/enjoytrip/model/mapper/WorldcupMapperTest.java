package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Worldcup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorldcupMapperTest {

    WorldcupMapper mapper;

    @Autowired
    public WorldcupMapperTest(WorldcupMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    void selectAll() {
        assertEquals(mapper.selectAll().size(),8);
    }

    @Test
    void select() {
        assertEquals(mapper.select(1).getContent(),"여기 해산물이 그렇게 맛있대요");
    }

    @Test
    @Transactional
    void update() {
        int id = 1;
        Worldcup w = mapper.select(id);
        int win = w.getWins();
        assertEquals(mapper.update(id),1);
        assertEquals(mapper.select(id).getWins(),win+1);
    }
}