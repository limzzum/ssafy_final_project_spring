package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.valid.LoginForm;
import com.ssafy.enjoytrip.model.mapper.UserMapper;
import com.ssafy.enjoytrip.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private UserMapper mapper;
	private RedisUtil redisUtil;

	@Autowired
	public UserServiceImpl(UserMapper mapper, RedisUtil redisUtil) {

		this.mapper = mapper;
		this.redisUtil = redisUtil;
	}

	@Override
	public User login(LoginForm loginForm) {
		User check = mapper.selectByUserId(loginForm.getUserId());
		if(check==null) return null;
		return (check.getUserPwd()!=null&&check.getUserPwd().equals(loginForm.getUserPwd()))?check:null;
	}

	@Override
	public void logout(String accessToken, int userNo) {
		redisUtil.setExcludeList(accessToken,"accessToken");
	}

	@Override
	public User selectById(int userNo) {
		return mapper.selectById(userNo);
	}

	@Override
	public User selectByUserId(String userId) {
		return mapper.selectByUserId(userId);
	}

	@Override
	public int regist(User user) {
		User check = mapper.selectByUserId(user.getUserId());

		// 중복 아이디 회원이 존재하는 경우 -1 리턴
		if(check!=null) return -1;

		// 성공시 1, 실패시 0 리턴
		return mapper.insert(user);
	}

	@Override
	public int delete(int userNo) {
		return mapper.delete(userNo);
	}

	@Override
	public int update(User user) {
		return mapper.update(user);
	}


}
