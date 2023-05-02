package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.SearchPlace;

public interface PlaceService {
	List<Place> searchPlace(SearchPlace searchPlace);
	Place getPlace(int contentId);

}
