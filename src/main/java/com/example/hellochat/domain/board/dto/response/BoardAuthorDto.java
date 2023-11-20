package com.example.hellochat.domain.board.dto.response;

import lombok.Builder;

@Builder
public record BoardAuthorDto (
    Long userid,
    String username
) {}
