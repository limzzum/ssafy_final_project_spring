package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;


public interface UserService {
	public User getUser(String id) throws SQLException;
	
	int registUser(User user) throws SQLException;
	
	public int deleteUser(User user) throws SQLException;
	
	public boolean updateUser(User user, String originalpw, String pass1, String pass2) throws SQLException;
	
}
