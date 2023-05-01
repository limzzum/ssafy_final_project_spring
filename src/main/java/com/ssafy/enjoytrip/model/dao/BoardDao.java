package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;

public interface BoardDao {
	int registNoticeBoard(String title, String content, User u) throws SQLException;
	public int getNoticeCnt() throws SQLException;
}
