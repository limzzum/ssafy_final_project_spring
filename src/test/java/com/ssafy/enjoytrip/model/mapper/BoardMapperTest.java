package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

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
        Board board = Board.builder().title("title").content("content").userNo(1).boardType("review").build();
        mapper.insert(board);

        Board selected = mapper.select(board.getPostId());
        assertEquals(board.getTitle(),selected.getTitle());
        assertEquals(board.getContent(), selected.getContent());
        assertEquals(board.getHits(),selected.getHits());
        assertEquals(board.getBoardType(), selected.getBoardType());

    }

    @Test
    void selectAll() {
        List<Board> boards = mapper.selectAll();
    }

    @ParameterizedTest
    @ValueSource(strings = { "review", "free", "notice", "team"})
    void selectAll(String type) {
        List<Board> boards = mapper.selectAllType(type);
        int max = Math.min(10, boards.size());
        for (int i = 0; i < max; i++) {
            assertEquals(boards.get(i).getBoardType(), type);
        }
    }

    @ParameterizedTest
    @CsvSource(value = { "review,1", "free,1", "notice,2", "team,2"})
    void selectAll(String type, int userNo) {
        List<Board> boards = mapper.selectAllTypeAndUser(type, userNo);
        int max = Math.min(10, boards.size());
        for (int i = 0; i < max; i++) {
            assertEquals(boards.get(i).getBoardType(), type);
            assertEquals(boards.get(i).getUserNo(), userNo);
        }
    }


    @Test
    void delete() {
        Board board = Board.builder().title("title").content("content").userNo(1).boardType("review").hits(1).build();
        mapper.insert(board);

        int result = mapper.delete(board.getPostId());
        Assertions.assertEquals(result, 1);
    }

    @Test
    void update() {
        Board board = Board.builder().title("title").content("content").userNo(1).boardType("review").hits(1).build();
        mapper.insert(board);

        board.setContent("change");
        int result = mapper.update(board);
        Assertions.assertEquals(result, 1);

        Board selected = mapper.select(board.getPostId());

        assertEquals(selected.getContent(), "change");

    }

    @Test
    void pageTest(){
        int perPage = 10;
        PageHelper.startPage(1,perPage);
        Page<Board> p = mapper.selectAll();
        System.out.println(p);
    }
}