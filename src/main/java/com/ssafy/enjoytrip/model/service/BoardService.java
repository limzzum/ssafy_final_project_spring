package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;

public interface BoardService {
	public int noticeWrite(String title, String content, User u) throws SQLException;
}
