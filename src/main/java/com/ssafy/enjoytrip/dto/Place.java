package com.ssafy.enjoytrip.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Place {
	int id;
	int type;
	String title;
	String addr1;
	String image;
	String sido;
	double lat;
	double lng;
}
