package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.form.LoginForm;


public interface UserService {
	User login(LoginForm loginForm);
	void logout(String accessToken, int userNo);

	User selectById(int userNo);
	User selectByUserId(String userId);
	
	int regist(User user);
	
	int delete(int userNo);
	
	int update(User user);
	
}
