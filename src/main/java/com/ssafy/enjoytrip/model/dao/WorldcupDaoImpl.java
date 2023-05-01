package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.enjoytrip.model.dto.Worldcup;
import com.ssafy.enjoytrip.util.DBUtil;

public class WorldcupDaoImpl implements WorldcupDao{
	private static WorldcupDaoImpl instance = new WorldcupDaoImpl();
	private WorldcupDaoImpl() {}
	public static WorldcupDaoImpl getWorldcupDao() {
		return instance;
	}
	@Override
	public ArrayList<Worldcup> getWorldcupList() throws SQLException {
		ArrayList<Worldcup> worldcups = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from worldcup;\n";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				worldcups.add(new Worldcup(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getInt(5)));
			}
		}finally {
			DBUtil.getInstance().close(rset,pstmt,con);
		}
		return worldcups;
	}
	@Override
	public Worldcup getWorldcup(int id) throws SQLException {
		Worldcup worldcup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from worldcup where id=?;\n";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				worldcup= new Worldcup(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getInt(5));
			}
		}finally {
			DBUtil.getInstance().close(rset,pstmt,con);
		}
		return worldcup;
	}
	@Override
	public int updateWorldcupWins(int id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = "update worldcup set wins=wins+1 where id=?;";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		}finally {
			DBUtil.getInstance().close(pstmt,con);
		}
		System.out.println("변경 완료");
		return result;
	}

}
