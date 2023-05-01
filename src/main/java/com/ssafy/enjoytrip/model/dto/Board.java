package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

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
public class Board {

	int postId; // AI, PK
	String title;
	Timestamp createTime;
	String content;
	int userNo;
	String boardType;
	int hits;

}
