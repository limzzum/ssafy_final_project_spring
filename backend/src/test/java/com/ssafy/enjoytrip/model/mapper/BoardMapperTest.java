package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.form.PostBoard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;
    @Test
    void insert() {
        PostBoard board = PostBoard.builder().title("title").content("content").userNo(1).boardType("review").build();
        int result = mapper.insert(board);

        Assertions.assertEquals(result, 1);

    }

    @Test
    void select() {
        PostBoard board = PostBoard.builder().title("title").content("content").userNo(1).boardType("review").build();
        mapper.insert(board);

        Board selected = mapper.select(board.getPostId());
        assertEquals(board.getTitle(),selected.getTitle());
        assertEquals(board.getContent(), selected.getContent());
        assertEquals(board.getBoardType(), selected.getBoardType());

    }

    @Test
    void search(){
        Map<String,Object> keys = new HashMap<>();
        keys.put("title","제주도");
        keys.put("boardType","review");
        keys.put("userId",2);
        log.info("search Test : "+mapper.search(keys));
    }


    @Test
    void delete() {
        PostBoard board = PostBoard.builder().title("title").content("content").userNo(1).boardType("review").build();
        mapper.insert(board);

        int result = mapper.delete(board.getPostId());
        Assertions.assertEquals(result, 1);
    }

//    @Test
//    void update() {
//        PostBoard board = PostBoard.builder().title("title").content("content").userNo(1).boardType("review").build();
//        mapper.insert(board);
//
//        board.setContent("change");
//        int result = mapper.update(board);
//        Assertions.assertEquals(result, 1);
//
//        Board selected = mapper.select(board.getPostId());
//
//        assertEquals(selected.getContent(), "change");
//
//    }

    @Test
    void pageTest(){
        int perPage = 10;
        PageHelper.startPage(1,perPage);
        Page<Board> p = mapper.search(new HashMap<>());
        System.out.println(p);
    }
}