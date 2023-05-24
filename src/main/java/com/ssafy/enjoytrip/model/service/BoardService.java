package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.form.PostBoard;

public interface BoardService {

	int insert(PostBoard board);
	int delete(int postId);
	int update(Board board);
	Board select(int postId);
	Page<Board> search(Map<String,Object> keys);

}
