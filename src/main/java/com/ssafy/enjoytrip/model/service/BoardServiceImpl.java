package com.ssafy.enjoytrip.model.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.form.PostBoard;
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
	public int insert(PostBoard board) {
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
	public Board select(int postId) {
		return mapper.select(postId);
	}

	@Override
	public Page<Board> search(Map<String,Object> keys) {
		return mapper.search(keys);
	}

}
