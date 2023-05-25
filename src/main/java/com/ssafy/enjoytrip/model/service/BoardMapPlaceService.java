package com.ssafy.enjoytrip.model.service;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.form.PostBoard;

import java.util.List;
import java.util.Map;

public interface BoardMapPlaceService {
    int insert(Map<String, Object> map);
    int delete(int contentId);
    List<Place> search(int postId);
}
