package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    Comment select(int commentId);
    int insert(Comment comment);
    List<Comment> search(Map<String,Object> keys);
    int delete(int commentId);
    int update(Comment comment);

}