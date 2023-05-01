package com.ssafy.enjoytrip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worldcup {
	int id;
	String title, content, img;
	int wins;
}