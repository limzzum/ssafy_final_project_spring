package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.ContentType;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.Sido;

public interface PlaceService {
	Page<Place> search(Place place);
	Place select(int contentId);
	List<ContentType> getContents();
	List<Sido> getRegions();

}
