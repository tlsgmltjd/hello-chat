package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record BoardsResponse (
    Long boardId,
    String title,
    String content,
    Integer commentCount,
    BoardAuthorDto author,
    Integer likeCount
) {}