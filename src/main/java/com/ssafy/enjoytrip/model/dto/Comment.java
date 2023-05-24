package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

public class Comment {
    int commentId; // AI, PK
    int postId;
    int parentId;
    String content;
    Timestamp createTime;

}
