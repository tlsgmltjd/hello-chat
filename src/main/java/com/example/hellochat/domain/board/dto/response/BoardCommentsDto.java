package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
public record BoardCommentsDto (
    Long id,
    String content,
    LocalDateTime date,
    BoardAuthorDto author
) {}
