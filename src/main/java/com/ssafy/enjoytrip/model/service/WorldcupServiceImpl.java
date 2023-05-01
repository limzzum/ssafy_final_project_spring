package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.dto.Worldcup;
import com.ssafy.enjoytrip.model.dao.WorldcupDaoImpl;

public class WorldcupServiceImpl implements WorldcupService{
	static private WorldcupServiceImpl instance = new WorldcupServiceImpl();
	private WorldcupServiceImpl() {}
	static public WorldcupServiceImpl getWorldcupService() {
		return instance;
	}
	@Override
	public ArrayList<Worldcup> getWorldcupList() throws SQLException {
		return WorldcupDaoImpl.getWorldcupDao().getWorldcupList();
	}
	@Override
	public Worldcup getWorldcup(int id) throws SQLException {
		return WorldcupDaoImpl.getWorldcupDao().getWorldcup(id);
	}
	@Override
	public int updateWorldcupWins(int id) throws SQLException {
		return WorldcupDaoImpl.getWorldcupDao().updateWorldcupWins(id);
	}
	
}
