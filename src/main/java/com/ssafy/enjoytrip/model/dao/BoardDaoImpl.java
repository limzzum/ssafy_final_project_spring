package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.enjoytrip.dto.User;
import com.ssafy.enjoytrip.util.DBUtil;

public class BoardDaoImpl implements BoardDao{
	private static BoardDaoImpl impl = new BoardDaoImpl();
	
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getBoardDaoImpl() {
		// TODO Auto-generated method stub
		return impl;
	}
	
	@Override
	public int registNoticeBoard(String title, String content, User u) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = "insert into noticeboard values(?,now(),?,?,?,?,0);";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, getNoticeCnt()+1);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, u.getName());
			pstmt.setString(5, u.getId());
			result = pstmt.executeUpdate();
		}finally {
			DBUtil.getInstance().close(pstmt,con);
		}
		return result;
	}
	
	public int getNoticeCnt() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cnt = 0;
		String sql = "select * from noticeboard";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				cnt++;
			}
		}finally {
			DBUtil.getInstance().close(rset,pstmt,con);
		}
		System.out.println(cnt);
		return cnt;
	}
}
