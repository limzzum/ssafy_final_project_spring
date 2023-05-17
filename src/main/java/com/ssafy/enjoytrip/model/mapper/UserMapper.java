package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectById(int id);
    User selectByUserId(String id);
    int insert(User user);
    int delete(int userNo);
    int update(User user);
}
