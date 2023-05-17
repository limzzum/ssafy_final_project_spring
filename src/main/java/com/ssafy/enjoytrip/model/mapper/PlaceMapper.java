package com.ssafy.enjoytrip.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.enjoytrip.model.dto.ContentType;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    Page<Place> search(Place place);
    Place select(int contentId);
    List<ContentType> getContents();
    List<Sido> getRegions();

}
