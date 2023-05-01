package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.dto.Worldcup;

public interface WorldcupDao {
	ArrayList<Worldcup> getWorldcupList() throws SQLException;
	Worldcup getWorldcup(int id) throws SQLException;
	int updateWorldcupWins(int id) throws SQLException;
}
