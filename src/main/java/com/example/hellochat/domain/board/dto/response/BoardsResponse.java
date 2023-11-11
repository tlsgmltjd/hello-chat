package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardsResponse {
    private Long boardId;
    private String title;
    private String content;
    // comment
    private Integer commentCount;
    private BoardsAuthorDto author;
    private Integer likeCount;
}
