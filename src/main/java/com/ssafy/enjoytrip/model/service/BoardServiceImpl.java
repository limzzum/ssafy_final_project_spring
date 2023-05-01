package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	@Autowired
	public BoardServiceImpl(BoardMapper mapper){
		this.mapper = mapper;
	}

	@Override
	public int add(Board board) {
		return mapper.insert(board);
	}

	@Override
	public int delete(int postId) {
		return mapper.delete(postId);
	}

	@Override
	public int update(Board board) {
		return mapper.update(board);
	}

	@Override
	public Board get(int postId) {
		return mapper.select(postId);
	}

	@Override
	public List<Board> getAll() {
		return mapper.selectAll();
	}

	@Override
	public List<Board> getAll(String type) {
		return mapper.selectAll(type);
	}

	@Override
	public List<Board> getAll(String type, int userNo) {
		return mapper.selectAll(type,userNo);
	}


}
