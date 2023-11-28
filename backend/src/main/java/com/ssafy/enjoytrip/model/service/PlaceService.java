package com.ssafy.enjoytrip.model.service;

import java.util.List;
import com.ssafy.enjoytrip.model.dto.ContentType;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.Sido;

public interface PlaceService {
	List<Place> search(Place place);
	Place select(int contentId);
	List<ContentType> getContents();
	List<Sido> getRegions();

}
