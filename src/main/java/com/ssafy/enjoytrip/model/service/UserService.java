package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.valid.LoginForm;


public interface UserService {
	User login(LoginForm loginForm);

	User selectById(int userNo);
	User selectByUserId(String userId);
	
	int regist(User user);
	
	int delete(int userNo);
	
	int update(User user);
	
}
