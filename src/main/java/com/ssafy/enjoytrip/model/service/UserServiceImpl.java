package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private UserMapper mapper;

	@Autowired
	public UserServiceImpl(UserMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public User login(User user) {
		User check = mapper.select(user.getUserId());
		return (check.getUserPwd()!=null&&check.getUserPwd().equals(user.getUserPwd()))?check:null;
	}

	@Override
	public int regist(User user) {
		return 0;
	}

	@Override
	public int delete(User user) {
		return 0;
	}

	@Override
	public boolean update(User user) {
		return false;
	}
//	@Override
//	public int registUser(User user) throws SQLException {
//		return UserDaoImpl.getUserDao().registUser(user);
//	}
//
//	@Override
//	public int deleteUser(User user) throws SQLException {
//		String id = user.getId();
//
//		return UserDaoImpl.getUserDao().deleteUser(id);
//	}
//	@Override
//	public boolean updateUser(User user, String originalpw, String pass1, String pass2) throws SQLException {
//
//		if(pass1.equals(pass2) && originalpw.equals(user.getPassword())) {
//			UserDaoImpl.getUserDao().updateUser(pass1, user.getId());
//			return true;
//		}else {
//			return false;
//		}
//	}
}
