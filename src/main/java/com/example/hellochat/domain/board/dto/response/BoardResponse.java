package com.example.hellochat.domain.board.dto.response;

public record BoardResponse (
    Long boardId,
    String title,
    BoardAuthorDto author,
    comments
) {}
