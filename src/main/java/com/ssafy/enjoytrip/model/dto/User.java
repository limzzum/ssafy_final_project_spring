package com.ssafy.enjoytrip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	int userNo;
	String userId;
	String userName;
	String userPwd;
	String email;
	String isAdmin;
}
