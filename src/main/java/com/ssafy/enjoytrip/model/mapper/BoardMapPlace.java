package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapPlace {
    int insert(Map<String, Object> map);
    List<Place> search(int postId);
    int delete(int contentId);
}
