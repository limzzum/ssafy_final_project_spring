package com.ssafy.enjoytrip.model.service;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Comment;
import com.ssafy.enjoytrip.model.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CommentServiceImpl implements CommentService{

    private CommentMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public int insert(Comment comment) {
        return mapper.insert(comment);
    }

    @Override
    public int delete(int commentId) {
        return mapper.delete(commentId);
    }

    @Override
    public int update(Comment comment) {
        return mapper.update(comment);
    }

    @Override
    public Page<Comment> search(Map<String, Object> keys) {
        return mapper.search(keys);
    }
}
