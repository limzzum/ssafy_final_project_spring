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
	int contentId;
	int contentTypeId;
	String title;
	String addr1;
	String firstImage;
	int sidoCode;
	int gugunCode;
	double latitude;
	double longitude;

	String overview;
	String contentTypeName;
	String sidoName;

}
