package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;

public interface BoardService {

	int add(Board board);
	int delete(int postId);
	int update(Board board);
	Board get(int postId);
	List<Board> getAll();

}
