package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.dto.Worldcup;

public interface WorldcupService {
	ArrayList<Worldcup> getWorldcupList() throws SQLException;
	Worldcup getWorldcup(int id) throws SQLException;
	int updateWorldcupWins(int id) throws SQLException;
}
