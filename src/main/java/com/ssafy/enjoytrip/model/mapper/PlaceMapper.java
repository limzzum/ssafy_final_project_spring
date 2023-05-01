package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    List<Place> search(Place place);

    List select(int contentId);

}
