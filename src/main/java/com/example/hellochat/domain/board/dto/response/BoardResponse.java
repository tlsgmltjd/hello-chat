package com.example.hellochat.domain.board.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BoardResponse (
    Long boardId,
    String title,
    String content,
    BoardAuthorDto author,
    List<BoardCommentsDto> comments
) {}
