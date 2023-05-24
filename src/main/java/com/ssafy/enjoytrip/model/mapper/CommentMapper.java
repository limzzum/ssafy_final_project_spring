package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
    Page<Comment> search(Map<String,Object> keys);
    int delete(int commentId);
    int update(Comment comment);

}