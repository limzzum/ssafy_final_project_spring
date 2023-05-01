package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.ssafy.enjoytrip.dto.Place;
import com.ssafy.enjoytrip.util.DBUtil;

public class PlaceDaoImpl implements PlaceDao{
	private static PlaceDaoImpl instance = new PlaceDaoImpl();
	private PlaceDaoImpl() {}
	public static PlaceDaoImpl getPlaceDao() {
		return instance;
	}

	@Override
	public ArrayList<Place> searchPlace(int sido, int type, String keyword) throws SQLException{
		ArrayList<Place> places = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select content_id,content_type_id,title,addr1,first_image,sido_code,latitude,longitude from attraction_info where content_type_id = ? and sido_code = ? and title like ? limit 0,10;\n";
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, sido);
			pstmt.setString(3, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				places.add(new Place(rset.getInt(1),rset.getInt(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),rset.getDouble(7),rset.getDouble(8)));
			}
		}finally {
			DBUtil.getInstance().close(rset,pstmt,con);
		}
		
		Collections.sort(places, new Comparator<Place>() {
			@Override
			public int compare(Place o1, Place o2) {
				// TODO Auto-generated method stub
				if(o1.getTitle().compareTo(o2.getTitle()) == 0) {
					double myLat = 37.503325874722;
					double myLag = 127.04403462366;
					double diff1 = Math.abs(myLat - o1.getLat()) + Math.abs(myLag) - o1.getLng();
					double diff2 = Math.abs(myLat - o2.getLat()) + Math.abs(myLag) - o2.getLng();
					return Double.compare(diff1, diff2);
				}
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});
		
		for(int i=0; i<places.size(); i++) {
			System.out.println(places.get(i));
		}
		
		return places;
	}

	@Override
	public Place getPlace(String id) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

}
