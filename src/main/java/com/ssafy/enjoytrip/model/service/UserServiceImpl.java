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
		User check = mapper.select(user.getUserId());

		// 중복 아이디 회원이 존재하는 경우 -1 리턴
		if(check!=null) return -1;

		// 성공시 1, 실패시 0 리턴
		return mapper.insert(user);
	}

	@Override
	public int delete(User user) {
		return 0;
	}

	@Override
	public int update(User user) {
		return 0;
	}

}
