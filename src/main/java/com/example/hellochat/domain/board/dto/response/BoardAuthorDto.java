package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record BoardAuthorDto (
    Long userid,
    String username
) {}
