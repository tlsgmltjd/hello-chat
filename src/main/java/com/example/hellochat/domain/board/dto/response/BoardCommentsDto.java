package com.example.hellochat.domain.board.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardCommentsDto (
    Long id,
    String content,
    LocalDateTime date,
    BoardAuthorDto author
) {}
