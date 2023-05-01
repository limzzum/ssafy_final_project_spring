package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;

public interface UserDao {
	User getUser(String id) throws SQLException;
	int registUser(User user) throws SQLException;
	int deleteUser(String id) throws SQLException;
	int updateUser(String pass, String id) throws SQLException;
}
