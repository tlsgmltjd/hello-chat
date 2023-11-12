package com.example.hellochat.domain.board.dto.response;

import lombok.Builder;

@Builder
public record BoardResponse (
    Long boardId,
    String title,
    String content,
    BoardAuthorDto author,
    BoardCommentsDto comments
) {}
