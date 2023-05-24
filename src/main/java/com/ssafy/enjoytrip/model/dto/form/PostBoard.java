package com.ssafy.enjoytrip.model.dto.form;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostBoard {
    int postId; // AI, PK
    String title;
    String content;
    String boardType;
    int userNo;
    List<Integer> places;
}
