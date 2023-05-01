package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dao.PlaceDaoImpl;

public class PlaceServiceImpl implements PlaceService{
	static private PlaceServiceImpl instance = new PlaceServiceImpl();
	private PlaceServiceImpl() {}
	static public PlaceServiceImpl getPlaceService() {
		return instance;
	}

	@Override
	public ArrayList<Place> searchPlace(int sido, int type, String keyword) throws SQLException{
		return PlaceDaoImpl.getPlaceDao().searchPlace(sido, type, keyword);
	}

	@Override
	public Place getPlace(String id) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}
}
