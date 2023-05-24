package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

public class Comment {
    int commentId; // AI, PK
    int postId;
    int parentId; //0이 아니면 대댓글
    int userNo;
    String content;
    Timestamp createTime;

}
