package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insert(Board board);
    Board select(int postId);
    Page<Board> selectAll();
    Page<Board> selectAllType(String type);
    Page<Board> selectAllTypeAndUser(@Param("type") String type, @Param("userNo") int userNo);
    int delete(int postId);
    int update(Board board);

}
