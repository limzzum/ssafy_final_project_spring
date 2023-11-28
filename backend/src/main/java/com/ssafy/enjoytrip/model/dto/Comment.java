package com.ssafy.enjoytrip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    int commentId; // AI, PK
    int postId;
    int parentId; //0이 아니면 대댓글
    int userNo;
    String userName;
    List<Comment> subs;
    String content;
    Timestamp createTime;

}
