package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.model.dto.Place;

public interface PlaceDao {
	ArrayList<Place> searchPlace(int sido, int type, String keyword) throws SQLException;
	Place getPlace(String id) throws SQLException;
}
