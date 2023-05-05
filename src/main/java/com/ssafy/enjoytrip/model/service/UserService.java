package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;


public interface UserService {
	User login(User user);

	User select(String userId);
	
	int regist(User user);
	
	int delete(String userId);
	
	int update(User user);
	
}
