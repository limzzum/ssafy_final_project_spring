package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.SearchPlace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    List<Place> selectAll(SearchPlace searchPlace);
    Place select(int contentId);

}
