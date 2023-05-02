package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.SearchPlace;
import com.ssafy.enjoytrip.model.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{

    private PlaceMapper mapper;

    @Autowired
    public PlaceServiceImpl(PlaceMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public List<Place> searchPlace(SearchPlace searchPlace) {
        return mapper.selectAll(searchPlace);
    }

    @Override
    public Place getPlace(int contentId) {
        return mapper.select(contentId);
    }
}
