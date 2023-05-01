package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;
    @Test
    void insert() {
        Board board = Board.builder().title("title").content("content").userNo(1).boardType("review").hits(1).build();
        int result = mapper.insert(board);

        Assertions.assertEquals(result, 1);

    }

    @Test
    void select() {

    }

    @Test
    void selectAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}