package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
	static private UserServiceImpl instance = new UserServiceImpl();
	private UserServiceImpl() {}
	static public UserServiceImpl getUserService() {
		return instance;
	}
	@Override
	public User getUser(String id) throws SQLException {
		User u = UserDaoImpl.getUserDao().getUser(id);
		return u;
	}
	@Override
	public int registUser(User user) throws SQLException {
		return UserDaoImpl.getUserDao().registUser(user);
	}
	
	@Override
	public int deleteUser(User user) throws SQLException {
		String id = user.getId();
		
		return UserDaoImpl.getUserDao().deleteUser(id);
	}
	@Override
	public boolean updateUser(User user, String originalpw, String pass1, String pass2) throws SQLException {

		if(pass1.equals(pass2) && originalpw.equals(user.getPassword())) {
			UserDaoImpl.getUserDao().updateUser(pass1, user.getId());
			return true;
		}else {
			return false;
		}
	}
}
