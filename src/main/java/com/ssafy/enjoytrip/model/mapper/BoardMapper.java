package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int insert(Board board);
    Board select(int postId);
    Page<Board> search(Map<String,Object> keys);
    int delete(int postId);
    int update(Board board);

}
