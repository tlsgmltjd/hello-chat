package com.example.hellochat.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardAuthorDto {
    private final Long id;
    private final String username;
}
