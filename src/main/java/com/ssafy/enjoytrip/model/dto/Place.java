package com.ssafy.enjoytrip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Place {
	int id;
	int type;
	String title;
	String addr1;
	String image;
	int sido;
	double lat;
	double lng;
}
