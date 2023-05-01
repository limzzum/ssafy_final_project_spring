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
public class Board {
	int num;
	Date datetime;
	String title;
	String content;
	String writername;
	String writerid;
	int hits;
}
