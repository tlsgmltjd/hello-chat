package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardsResponse {
    private final Long boardId;
    private final String title;
    private final String content;
    private final Integer commentCount;
    private final BoardAuthorDto author;
    private final Integer likeCount;
}
