package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class BoardsResponse {
    private final Long boardId;
    private final String title;
    private final String content;
    private final Integer commentCount;
    private final BoardsAuthorDto author;
    private final Integer likeCount;
}
