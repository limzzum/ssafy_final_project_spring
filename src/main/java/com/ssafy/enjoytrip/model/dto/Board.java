package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {

	int postId; // AI, PK
	String title;
	Timestamp createTime;
	String content;
	int userNo;
	String boardType;
	int hits;
	String userName;
	String boardTitle;
}
