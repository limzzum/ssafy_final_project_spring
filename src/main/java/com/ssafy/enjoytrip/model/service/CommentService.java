package com.ssafy.enjoytrip.model.service;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.Comment;
import com.ssafy.enjoytrip.model.dto.form.PostBoard;

import java.util.Map;

public interface CommentService {

    int insert(Comment comment);
    int delete(int commentId);
    int update(Comment comment);
    Page<Comment> search(Map<String,Object> keys);
}
