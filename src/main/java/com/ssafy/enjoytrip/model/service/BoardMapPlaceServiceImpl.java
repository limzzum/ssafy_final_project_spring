package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.mapper.BoardMapPlace;
import com.ssafy.enjoytrip.model.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardMapPlaceServiceImpl implements BoardMapPlaceService{

    private BoardMapPlace mapper;

    @Autowired
    public BoardMapPlaceServiceImpl(BoardMapPlace mapper){
        this.mapper = mapper;
    }
    @Override
    public int insert(Map<String, Object> map) {
        return mapper.insert(map);
    }

    @Override
    public int delete(int postId) {
        return mapper.delete(postId);
    }

    @Override
    public List<Place> search(int postId) {
        return mapper.search(postId);
    }
}
