package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User select(String id);
    int insert(User user);
    int delete(String id);
    int update(User user);
}
