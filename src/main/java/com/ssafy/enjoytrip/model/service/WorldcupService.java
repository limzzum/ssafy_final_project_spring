package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.Worldcup;

public interface WorldcupService {
	List<Worldcup> getWorldcupList();
	Worldcup getWorldcup(int id);
	int updateWorldcupWins(int id);
}
