package com.ssafy.enjoytrip.model.service;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.ContentType;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.Sido;
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
    public Page<Place> search(Place place) {
        return mapper.search(place);
    }

    @Override
    public Place select(int contentId) {
        return mapper.select(contentId);
    }

    @Override
    public List<ContentType> getContents() {
        return mapper.getContents();
    }

    @Override
    public List<Sido> getRegions() {
        return mapper.getRegions();
    }
}
