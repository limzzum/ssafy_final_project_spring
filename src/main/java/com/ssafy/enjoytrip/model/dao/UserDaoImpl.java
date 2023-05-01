package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.util.DBUtil;

public class UserDaoImpl implements UserDao{
	private static UserDaoImpl instance = new UserDaoImpl();
	private UserDaoImpl() {}
	public static UserDaoImpl getUserDao() {
		return instance;
	}

	@Override
	public User getUser(String id) throws SQLException {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from user where id = ? ;";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				user = new User(rset.getString(1),rset.getString(2),rset.getString(3),rset.getString(4));
			}
		}finally {
			DBUtil.getInstance().close(rset,pstmt,con);
		}
		return user;
	}

	@Override
	public int registUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = "INSERT INTO `user` VALUES (?,?,?,?);";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			result = pstmt.executeUpdate();
		}finally {
			DBUtil.getInstance().close(pstmt,con);
		}
		return result;
	}

	
	@Override
	public int deleteUser(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = "DELETE FROM user WHERE id=?";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}finally {
			DBUtil.getInstance().close(pstmt,con);
		}
		System.out.println("삭제 완료");
		return result;
	}
	
	@Override
	public int updateUser(String pass, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = "update user set pass=? where id=?;";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		}finally {
			DBUtil.getInstance().close(pstmt,con);
		}
		System.out.println("변경 완료");
		return result;
	}

}
