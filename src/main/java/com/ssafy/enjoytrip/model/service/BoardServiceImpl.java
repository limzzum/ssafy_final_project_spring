package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;

public class BoardServiceImpl implements BoardService {

	private static BoardServiceImpl impl = new BoardServiceImpl();
	
	private BoardServiceImpl() {
	
	}
	
	public static BoardServiceImpl getImpl() {
		return impl;
	}
	
	@Override
	public int noticeWrite(String title, String content, User u) throws SQLException {
		//return BoardDaoImpl.getBoardDaoImpl().registNoticeBoard(title, content, u);
		return -1;
	}
	
}
