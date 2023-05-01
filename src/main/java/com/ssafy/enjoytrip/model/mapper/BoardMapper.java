package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insert(Board board);
    Board select(int postId);
    List<Board> selectAll();
    int delete(int postId);
    int update(Board board);

}
