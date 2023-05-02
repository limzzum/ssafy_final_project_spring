package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Worldcup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorldcupMapper {

    List<Worldcup> selectAll() ;
    Worldcup select(int id);
    int update(int id);

}
