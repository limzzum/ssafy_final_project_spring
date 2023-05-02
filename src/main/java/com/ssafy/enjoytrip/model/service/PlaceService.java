package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.model.dto.Place;

public interface PlaceService {
	ArrayList<Place> searchPlace(int sido, int type, String keyword);
	Place getPlace(String id);

}
